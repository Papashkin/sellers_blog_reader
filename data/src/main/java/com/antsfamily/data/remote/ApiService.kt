package com.antsfamily.data.remote

import com.antsfamily.data.models.PostApiModel
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    companion object {
        const val ENDPOINT = "https://paulsellers.com/wp-json/wp/v2/"
    }

    @GET("posts")
    suspend fun getPosts(
        @Query("page") page: Int
    ): List<PostApiModel>
}
