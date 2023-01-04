package com.antsfamily.sellersblogreader.ui.post

sealed class PostUiState {
    object Loading: PostUiState()
    data class Content(val post: PostItem): PostUiState()
    data class Error(val errorMessage: String): PostUiState()
}
