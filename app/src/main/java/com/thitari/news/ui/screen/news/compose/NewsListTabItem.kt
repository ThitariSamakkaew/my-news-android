package com.thitari.news.ui.screen.news.compose

import androidx.annotation.StringRes
import com.thitari.news.R

enum class NewsListTabItem(@StringRes val title: Int) {
    BUSINESS(R.string.news_tab_item_business),
    POLITICAL(R.string.news_tab_item_political),
    SPORT(R.string.news_tab_item_sport),
    NATIONAL(R.string.news_tab_item_national)
}
