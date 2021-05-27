package com.decagon.blogapplicationmvi.data.api

import com.decagon.blogapplicationmvi.data.model.Post

interface ApiHelper {
    suspend fun getPosts(): List<Post>
}