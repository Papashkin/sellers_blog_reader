package com.antsfamily.data.remote

import com.antsfamily.data.models.PostApiModel
import javax.inject.Inject

class RemoteSourceImpl @Inject constructor(
    private val service: ApiService
) : RemoteSource {

    override suspend fun getPosts(page: Int): List<PostApiModel> = service.getPosts(page)
}
