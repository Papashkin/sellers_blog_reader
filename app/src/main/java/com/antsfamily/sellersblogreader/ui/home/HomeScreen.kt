package com.antsfamily.sellersblogreader.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.antsfamily.data.models.PostApiModel
import com.antsfamily.sellersblogreader.R
import com.antsfamily.sellersblogreader.ui.theme.FontSize
import com.antsfamily.sellersblogreader.ui.theme.Padding

interface HomeScreen {
    companion object {
        @Composable
        fun Content(navController: NavController) {
            HomeScreen(navController)
        }
    }
}

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state = viewModel.state.collectAsState()
    when {
        state.value.isLoading -> FullScreenLoading()
        state.value.isContentVisible ->
            HomeScreenPostsContent(state.value.content, viewModel::onItemClicked) {
                viewModel.onLoadMore()
            }
        state.value.isErrorVisible -> {}
    }
}

@Preview
@Composable
fun FullScreenLoading() {
    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxSize()) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            CircularProgressIndicator()
        }
    }
}


@Composable
fun HomeScreenPostsContent(
    posts: List<PostApiModel>,
    onItemClick: (Int) -> Unit,
    onLoadMore: () -> Unit,
) {
    val scrollState = rememberLazyListState()
    Column {
        Text(
            text = stringResource(R.string.home_title),
            modifier = Modifier.padding(top = Padding.medium, start = Padding.medium),
            style = TextStyle(fontSize = FontSize.H4, fontWeight = FontWeight.Medium),
        )
        Column(
            modifier = Modifier
                .padding(top = Padding.medium)
                .fillMaxWidth()
        ) {
            LazyColumn(
                contentPadding = PaddingValues(all = Padding.medium),
                state = scrollState,
            ) {
                itemsIndexed(posts) { index, post ->
                    PostItemCard(post) { onItemClick(it) }
                    if (index < posts.lastIndex)
                        Divider(
                            color = MaterialTheme.colors.onSecondary,
                            thickness = 1.dp
                        )
                }
            }
            scrollState.OnBottomReached(2) {
                onLoadMore.invoke()
            }
        }
    }
}


@Composable
fun LazyListState.OnBottomReached(
    buffer: Int = 0,
    loadMore: () -> Unit
) {
    require(buffer >= 0) { "Buffer should not be less than 0, but was set as $buffer"}
    val shouldLoadMore = remember {
        derivedStateOf {
            val lastVisibleItem =
                layoutInfo.visibleItemsInfo.lastOrNull() ?: return@derivedStateOf true

            lastVisibleItem.index == layoutInfo.totalItemsCount - 1 - buffer
        }
    }

    LaunchedEffect(shouldLoadMore) {
        snapshotFlow { shouldLoadMore.value }
            .collect {
                if (it) loadMore.invoke()
            }
    }
}
