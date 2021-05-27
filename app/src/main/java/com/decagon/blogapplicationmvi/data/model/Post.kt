package com.decagon.blogapplicationmvi.data.model

data class Post (
    val userId: Int,
    val id: Int,
    val body: String,
    val title: String
)