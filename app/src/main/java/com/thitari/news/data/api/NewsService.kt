package com.thitari.news.data.api

import com.thitari.news.data.api.NewApiImpl.Companion.API_KEY
import com.thitari.news.data.api.response.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

internal interface NewsService {

    @GET("everything")
    suspend fun getNews(
        @Query("q") query: String,
        @Query("apiKey") apiKey: String = API_KEY
    ): NewsResponse
}
