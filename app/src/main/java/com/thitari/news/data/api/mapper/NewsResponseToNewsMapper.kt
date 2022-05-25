package com.thitari.news.data.api.mapper

import com.thitari.news.data.api.response.ArticleResponse
import com.thitari.news.data.api.response.NewsResponse
import com.thitari.news.data.model.Article
import com.thitari.news.data.model.News
import javax.inject.Inject

interface NewsResponseToNewsMapper {
    fun map(news: NewsResponse): News
}

class NewsResponseToNewsMapperImpl @Inject constructor() : NewsResponseToNewsMapper {

    override fun map(news: NewsResponse): News {
        return News(
            status = news.status.orEmpty(),
            totalResults = news.totalResults ?: 0,
            articles = news.articles?.map { articleResponse ->
                articleResponse.map()
            } ?: emptyList()
        )
    }

    private fun ArticleResponse.map(): Article {
        return Article(
            sourceName = source?.name.orEmpty(),
            author = author.orEmpty(),
            title = title.orEmpty(),
            description = description.orEmpty(),
            url = url.orEmpty(),
            urlToImage = urlToImage.orEmpty(),
            publishedAt = publishedAt.orEmpty(),
            content = content.orEmpty()
        )
    }
}
