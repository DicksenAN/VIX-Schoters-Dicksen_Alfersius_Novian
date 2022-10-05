package com.example.vix_schoters_dicksenalfersiusnovian.api

import com.example.vix_schoters_dicksenalfersiusnovian.models.NewsArticle
import com.example.vix_schoters_dicksenalfersiusnovian.utils.Constants.Companion.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsAPI {
    @GET("v2/top-headlines")
    suspend fun getNews(
        @Query("country")
        countryCode: String = "us",
        @Query("page")
        pageNumber: Int = 1,
        @Query("apiKey")
        apiKey: String = API_KEY
    ): Response<NewsArticle>

}