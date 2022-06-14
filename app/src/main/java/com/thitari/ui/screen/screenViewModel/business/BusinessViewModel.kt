package com.thitari.ui.screen.screenViewModel.business

import com.thitari.news.data.repository.NewsRepository
import com.thitari.ui.screen.BaseNewsViewModel
import com.thitari.ui.screen.compose.Query
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BusinessViewModel @Inject constructor(
    newsRepository: NewsRepository,
) : BaseNewsViewModel(newsRepository) {

    override val query: Query
      get() = Query.BUSINESS
}
