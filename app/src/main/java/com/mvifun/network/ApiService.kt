package com.mvifun.network.dto

import retrofit2.http.GET

interface ApiService {

    @GET("posts")
    suspend fun getPosts() : PostResponseDto

}