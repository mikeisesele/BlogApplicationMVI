package com.decagon.blogapplicationmvi.data.api

import com.decagon.blogapplicationmvi.data.model.Post

class ApiHelperImpl(private val apiService: ApiService): ApiHelper {

    override suspend fun getPosts(): List<Post> {
       return apiService.getPosts()
    }

}