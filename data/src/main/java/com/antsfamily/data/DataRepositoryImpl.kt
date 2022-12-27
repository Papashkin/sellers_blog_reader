package com.antsfamily.data

import com.antsfamily.data.models.PostApiModel
import com.antsfamily.data.remote.RemoteSource
import javax.inject.Inject

class DataRepositoryImpl @Inject constructor(
    private val remoteSource: RemoteSource
): DataRepository {

    override suspend fun getPosts(page: Int): List<PostApiModel> = remoteSource.getPosts(page)
}
