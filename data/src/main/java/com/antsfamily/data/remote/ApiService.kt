package com.antsfamily.data.remote

import com.antsfamily.data.models.PostApiModel
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Path

interface ApiService {

    companion object {
        const val ENDPOINT = "https://paulsellers.com/wp-json/wp/v2/"
    }

    @GET("posts")
    suspend fun getPosts(@Query("page") page: Int): List<PostApiModel>

    @GET("posts/{id}")
    suspend fun getPost(@Path("id") id: Int): PostApiModel
}
