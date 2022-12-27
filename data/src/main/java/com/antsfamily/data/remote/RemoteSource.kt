package com.antsfamily.data.remote

import com.antsfamily.data.models.PostApiModel

interface RemoteSource {
    suspend fun getPosts(page: Int): List<PostApiModel>
}
