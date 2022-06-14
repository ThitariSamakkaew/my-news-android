package com.thitari.news.data.api

import com.thitari.news.data.api.mapper.NewsResponseToNewsMapper
import com.thitari.news.data.api.response.NewsResponse
import com.thitari.news.data.model.News
import javax.inject.Inject

interface NewsApi {
    suspend fun getNews(query: String, page: Int, pageSize: Int): News
}

internal class NewApiImpl @Inject constructor(
    private val newsService: NewsService,
    private val newsResponseToNewsMapper: NewsResponseToNewsMapper,
) : NewsApi {

    override suspend fun getNews(query: String, page: Int, pageSize: Int): News {
        val newsResponse: NewsResponse = newsService.getNews(query, page, pageSize)
        val news: News = newsResponseToNewsMapper.map(newsResponse)
        return news
    }

    companion object {
        const val BASE_URL = "https://newsapi.org/v2/"
        const val API_KEY = "7481fbf1715141dcb8e3ae9035cfbdf7"
    }
}
