package com.decagon.blogapplicationmvi.data.api

import com.decagon.blogapplicationmvi.data.model.Post
import retrofit2.http.GET

interface ApiService {

    @GET("/posts")
    suspend fun getPosts(): List<Post>

}