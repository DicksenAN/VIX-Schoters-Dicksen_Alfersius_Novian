package com.example.vix_schoters_dicksenalfersiusnovian.repository

import com.example.vix_schoters_dicksenalfersiusnovian.api.RetrofitInstance
import com.example.vix_schoters_dicksenalfersiusnovian.database.ArticleDatabase
import com.example.vix_schoters_dicksenalfersiusnovian.models.Article


//To to data management CRUD
class NewsRepository(
    val db: ArticleDatabase
) {
    suspend fun getNewsList(countryCode: String, pageNumber: Int) =
        RetrofitInstance.api.getNews(countryCode, pageNumber)

    suspend fun upsert(article: Article) = db.getArticleDao().upsert(article)

    fun getBookmarkedNews() = db.getArticleDao().getAllArticles()

    suspend fun deleteArticle(article: Article) = db.getArticleDao().deleteArticle(article)
}