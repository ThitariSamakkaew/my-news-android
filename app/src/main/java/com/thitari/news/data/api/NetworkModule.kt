package com.thitari.news.data.api

import com.thitari.news.data.api.mapper.NewsResponseToNewsMapper
import com.thitari.news.data.api.mapper.NewsResponseToNewsMapperImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class NetworkModule {

    @Singleton
    @Binds
    abstract fun bindNewsApi(impl: NewApiImpl): NewsApi

    @Singleton
    @Binds
    abstract fun bindNewsResponseToNewsMapper(impl: NewsResponseToNewsMapperImpl): NewsResponseToNewsMapper

    companion object {
        @Singleton
        @Provides

        fun providesNewsService(): NewsService = Retrofit
            .Builder()
            .baseUrl(NewApiImpl.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsService::class.java)
    }
}
