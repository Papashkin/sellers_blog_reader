package com.antsfamily.data

import com.antsfamily.data.models.PostApiModel

interface DataRepository {
    suspend fun getPosts(page: Int): List<PostApiModel>
}
