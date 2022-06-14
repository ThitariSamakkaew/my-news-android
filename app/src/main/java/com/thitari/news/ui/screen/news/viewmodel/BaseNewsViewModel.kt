package com.thitari.news.ui.screen.news.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.thitari.news.data.model.Article
import com.thitari.news.data.repository.NewsRepository
import com.thitari.news.data.util.NewsSource
import com.thitari.news.data.model.Query
import kotlinx.coroutines.flow.Flow

abstract class BaseNewsViewModel constructor(
    private val newsRepository: NewsRepository,
) : ViewModel() {

    abstract val query: Query

    val articles: Flow<PagingData<Article>> = Pager(
        PagingConfig(pageSize = 5)
    ) {
        NewsSource(newsRepository, query)
    }.flow.cachedIn(viewModelScope)
}
