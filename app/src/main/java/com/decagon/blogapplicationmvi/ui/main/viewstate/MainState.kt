package com.decagon.blogapplicationmvi.ui.main.viewstate

import com.decagon.blogapplicationmvi.data.model.Post

sealed class MainState {
    object Idle: MainState()
    object Loading: MainState()
    data class Posts(val post: List<Post>) : MainState()
    data class Error(val error: String?): MainState()
}