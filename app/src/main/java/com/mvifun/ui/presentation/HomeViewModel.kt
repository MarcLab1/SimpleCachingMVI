package com.mvifun.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mvifun.usecases.DeletePostUseCase
import com.mvifun.usecases.GetPostsFlowUseCase
import com.mvifun.ui.presentation.HomeEvent
import com.mvifun.ui.presentation.HomeState
import com.mvifun.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getPostsFlowUseCase: GetPostsFlowUseCase,
    private val deletePostUseCase: DeletePostUseCase
) : ViewModel() {

    var homeState by mutableStateOf(HomeState())
        private set

    init {
        getPostsFlow()
    }

    fun handleEvent(event: HomeEvent): Unit {
        when (event) {
            is HomeEvent.OnPress -> {
                homeState = homeState.copy(status = "OnPress = " + event.id.toString())
            }
            is HomeEvent.OnLongPress -> {
                homeState = homeState.copy(status = "OnLongPress = " + event.id.toString())
            }
            is HomeEvent.OnDeleteClick -> {
                viewModelScope.launch { deletePostUseCase.invoke(id = event.id) }
            }
        }
    }

    private fun getPostsFlow() {
        viewModelScope.launch {
            getPostsFlowUseCase.invoke().collect { result ->
                when (result) {
                    is Resource.Loading -> {
                        homeState = homeState.copy(
                            posts = result.data ?: emptyList(),
                            loading = true,
                            error = ""
                        )
                    }
                    is Resource.Success -> {
                        homeState = homeState.copy(
                            posts = result.data ?: emptyList(),
                            loading = false,
                            error = ""
                        )
                    }
                    is Resource.Error -> {
                        homeState = homeState.copy(
                            posts = result.data ?: emptyList(),
                            loading = false,
                            error = result.error.toString()
                        )
                    }
                }
            }
        }
    }
}

