package com.thitari.news.data.api.response

import com.google.gson.annotations.SerializedName

data class NewsResponse(
    @SerializedName("status") val status: String?,
    @SerializedName("totalResults") val totalResults: Int?,
    @SerializedName("articles") val articles: List<ArticleResponse>?
)
