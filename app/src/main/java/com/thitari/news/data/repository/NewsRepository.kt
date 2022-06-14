package com.thitari.news.data.repository

import com.thitari.news.data.api.NewsApi
import com.thitari.news.data.model.News
import com.thitari.news.data.model.Query
import javax.inject.Inject

interface NewsRepository {
    suspend fun getNews(query: Query, page: Int, pageSize: Int): News
}

internal class NewsRepositoryImpl @Inject constructor(
    private val newsApi: NewsApi,
) : NewsRepository {

    override suspend fun getNews(query: Query, page: Int, pageSize: Int): News {
        return newsApi.getNews(query.value, page, pageSize)
    }
}
