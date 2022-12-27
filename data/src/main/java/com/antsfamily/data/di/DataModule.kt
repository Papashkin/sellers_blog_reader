package com.antsfamily.data.di

import com.antsfamily.data.DataRepository
import com.antsfamily.data.DataRepositoryImpl
import com.antsfamily.data.remote.RemoteSource
import com.antsfamily.data.remote.RemoteSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class DataModule {

    @Binds
    abstract fun bindsDataRepository(dataRepositoryImpl: DataRepositoryImpl): DataRepository

    @Binds
    abstract fun bindsRemoteSource(remoteSourceImpl: RemoteSourceImpl): RemoteSource
}
