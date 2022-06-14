package com.thitari.news.data.util

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.thitari.news.data.model.Article
import com.thitari.news.data.repository.NewsRepository
import com.thitari.news.data.model.Query

class NewsSource constructor(
    private val newsRepository: NewsRepository,
    private val query: Query,
) : PagingSource<Int, Article>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        return try {
            val nextPage = params.key ?: 1
            val news = newsRepository.getNews(
                query = query,
                page = nextPage,
                pageSize = 5
            )
            LoadResult.Page(
                data = news.articles,
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = if (news.articles.isEmpty()) null else nextPage + 1)
        } catch (exception: Exception) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return state.anchorPosition
    }
}
