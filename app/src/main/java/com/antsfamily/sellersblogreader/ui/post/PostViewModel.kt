package com.antsfamily.sellersblogreader.ui.post

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.antsfamily.data.DataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(
    private val repository: DataRepository,
) : ViewModel() {

    private val _state = MutableStateFlow<PostUiState>(PostUiState.Loading)
    val state: StateFlow<PostUiState>
        get() = _state.asStateFlow()

    fun onViewCreated(id: Int) {
        getPostContent(id)
    }

    private fun getPostContent(id: Int) = viewModelScope.launch {
        try {
            val postContent = repository.getPostContent(id).mapToItem()
            _state.update { PostUiState.Content(postContent) }
        } catch (e: Exception) {
            handleError(e)
        }
    }

    private fun handleError(error: Exception) {
        _state.update { PostUiState.Error(error.message.orEmpty()) }
    }
}
