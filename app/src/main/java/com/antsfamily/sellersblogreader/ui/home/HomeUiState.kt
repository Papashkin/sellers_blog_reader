package com.antsfamily.sellersblogreader.ui.home

import androidx.compose.runtime.mutableStateListOf
import com.antsfamily.data.models.PostApiModel

data class HomeUiState(
    val isLoading: Boolean = true,
    val content: List<PostApiModel> = mutableStateListOf(),
    val isContentVisible: Boolean = false,
    val isErrorVisible: Boolean = false,
    val errorMessage: String? = null
)
