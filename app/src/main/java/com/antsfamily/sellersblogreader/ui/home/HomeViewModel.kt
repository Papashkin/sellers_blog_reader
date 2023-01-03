package com.antsfamily.sellersblogreader.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.antsfamily.data.DataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: DataRepository,
) : ViewModel() {

    private val _state = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
    val state: StateFlow<HomeUiState>
        get() = _state.asStateFlow()

    private var page: Int = 1

    init {
        getPosts()
    }

    fun onItemClicked(id: Int) {
        // TODO implement the navigation to Post screen
    }

    fun onLoadMore() {
        page++
        getPosts()
    }

    private fun getPosts() = viewModelScope.launch {
        try {
            val newPosts = repository.getPosts(page).map {
                it.mapToItem()
            }
            val allPosts = (state.value as? HomeUiState.Content)?.content.orEmpty().plus(newPosts)
            _state.update { HomeUiState.Content(allPosts) }
        } catch (e: Exception) {
            handleError(e)
        }
    }

    private fun handleError(error: Exception) {
        if ((state.value as? HomeUiState.Content)?.content.isNullOrEmpty()) {
            _state.update { HomeUiState.Error(error.message.orEmpty()) }
        }
    }
}
