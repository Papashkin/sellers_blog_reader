package com.antsfamily.sellersblogreader.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.antsfamily.sellersblogreader.R
import com.antsfamily.sellersblogreader.navigation.Screen
import com.antsfamily.sellersblogreader.ui.common.FullScreenLoading
import com.antsfamily.sellersblogreader.ui.theme.FontSize
import com.antsfamily.sellersblogreader.ui.theme.Padding

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state = viewModel.state.collectAsState()
    when (val stateValue = state.value) {
        HomeUiState.Loading -> FullScreenLoading()
        is HomeUiState.Content -> HomeScreenPostsContent(
            stateValue.content,
            viewModel::onLoadMore
        ) {
            navController.navigate("${Screen.Post.route.substringBefore("/")}/$it")
        }
        is HomeUiState.Error -> {
            //TODO add error handler
        }
    }
}

@Composable
fun HomeScreenPostsContent(
    posts: List<HomeItem>,
    onLoadMore: () -> Unit,
    onItemClick: (Int) -> Unit,
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
    require(buffer >= 0) { "Buffer should not be less than 0, but was set as $buffer" }
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
