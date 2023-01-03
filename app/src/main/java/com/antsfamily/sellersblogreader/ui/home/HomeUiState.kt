package com.antsfamily.sellersblogreader.ui.home

sealed class HomeUiState {
    object Loading: HomeUiState()
    data class Content(val content: List<HomeItem>): HomeUiState()
    data class Error(val errorMessage: String): HomeUiState()
}
