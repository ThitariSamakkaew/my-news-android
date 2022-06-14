package com.thitari.news.data.repository

import com.thitari.news.data.api.NetworkModule
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module(includes = [NetworkModule::class])

@InstallIn(SingletonComponent::class)
internal abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun bindNewsRepository(impl: NewsRepositoryImpl): NewsRepository
}
