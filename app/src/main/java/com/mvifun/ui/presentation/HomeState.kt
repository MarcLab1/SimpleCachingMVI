package com.mvifun.ui.presentation

import com.mvifun.model.Post

data class HomeState(
    val posts: List<Post> = emptyList(),
    val status : String = "",
    val loading: Boolean = false,
    val error: String = ""
)
