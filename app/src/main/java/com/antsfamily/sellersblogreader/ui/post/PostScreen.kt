package com.antsfamily.sellersblogreader.ui.post

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.antsfamily.sellersblogreader.ui.common.FullScreenLoading
import com.antsfamily.sellersblogreader.ui.common.HtmlText
import com.antsfamily.sellersblogreader.ui.theme.FontSize
import com.antsfamily.sellersblogreader.ui.theme.Padding
import com.antsfamily.sellersblogreader.ui.theme.Typography

@Composable
fun PostScreen(
    navController: NavController,
    id: Int,
    viewModel: PostViewModel = hiltViewModel()
) {
    viewModel.onViewCreated(id)
    val state = viewModel.state.collectAsState()
    when (val stateValue = state.value) {
        PostUiState.Loading -> FullScreenLoading()
        is PostUiState.Content -> PostScreenContent(stateValue.post) {
            navController.navigateUp()
        }
        is PostUiState.Error -> {
            //TODO add error handler
        }
    }
}

@Composable
fun PostScreenContent(post: PostItem, onBackClick: () -> Unit) {
    val scrollState = rememberScrollState()
    Column(Modifier.verticalScroll(scrollState)) {
        Icon(
            imageVector = Icons.Rounded.ArrowBack,
            contentDescription = null,
            modifier = Modifier
                .padding(Padding.medium)
                .clickable { onBackClick() }
        )
        Text(
            text = post.title,
            modifier = Modifier.padding(
                top = Padding.small, start = Padding.medium, end = Padding.medium
            ),
            style = TextStyle(fontSize = FontSize.H4, fontWeight = FontWeight.Medium),
        )
        Text(
            text = post.date,
            modifier = Modifier.padding(
                top = Padding.medium, start = Padding.medium, end = Padding.medium
            ),
            style = TextStyle(fontSize = FontSize.Caption, fontWeight = FontWeight.Medium),
        )
        HtmlText(
            text = post.content,
            style = Typography.body1,
            modifier = Modifier.padding(
                top = Padding.large, start = Padding.medium, end = Padding.medium
            ),
        )
    }
}
