package com.decagon.blogapplicationmvi.data.repository

import com.decagon.blogapplicationmvi.data.api.ApiHelper

class MainRepository(private val apiHelper: ApiHelper) {

    suspend fun getPosts() = apiHelper.getPosts()

}