package com.antsfamily.sellersblogreader.ui.home

import androidx.lifecycle.viewModelScope
import com.antsfamily.data.DataRepository
import com.antsfamily.sellersblogreader.ui.StatefulViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: DataRepository,
) : StatefulViewModel<HomeUiState>(HomeUiState()) {

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
            val newPosts = repository.getPosts(page)
            val allPosts = state.value.content.plus(newPosts)
            updateState {
                it.copy(
                    isLoading = false,
                    isContentVisible = allPosts.isNotEmpty(),
                    content = allPosts,
                    isErrorVisible = false,
                )
            }
        } catch (e: Exception) {
            handleError(e)
        }
    }

    private fun handleError(error: Exception) {
        if (state.value.content.isEmpty()) {
            updateState {
                it.copy(
                    isLoading = false,
                    isContentVisible = false,
                    isErrorVisible = true,
                    errorMessage = error.message
                )
            }
        }
    }
}
