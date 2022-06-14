package com.thitari.news.ui.screen.news.viewmodel

import com.thitari.news.data.repository.NewsRepository
import com.thitari.news.data.model.Query
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BusinessViewModel @Inject constructor(
    newsRepository: NewsRepository,
) : BaseNewsViewModel(newsRepository) {

    override val query: Query
      get() = Query.BUSINESS
}
