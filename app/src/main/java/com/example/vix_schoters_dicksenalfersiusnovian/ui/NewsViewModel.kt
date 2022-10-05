package com.example.vix_schoters_dicksenalfersiusnovian.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vix_schoters_dicksenalfersiusnovian.models.Article
import com.example.vix_schoters_dicksenalfersiusnovian.models.NewsArticle
import com.example.vix_schoters_dicksenalfersiusnovian.repository.NewsRepository
import com.example.vix_schoters_dicksenalfersiusnovian.utils.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class NewsViewModel(
    val newsRepository: NewsRepository
) : ViewModel() {

    val newsList: MutableLiveData<Resource<NewsArticle>> = MutableLiveData()
    var newsListPage = 1
    var newsListResponse: NewsArticle? = null


    init {
        getNewsList("us")
    }

    fun getNewsList(countryCode: String) = viewModelScope.launch {
        newsList.postValue(Resource.Loading())
        val response = newsRepository.getNewsList(countryCode, newsListPage)
        newsList.postValue(handleNewsListResponse(response))
    }


    private fun handleNewsListResponse(response: Response<NewsArticle>): Resource<NewsArticle> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                newsListPage++
                if (newsListResponse == null) {
                    newsListResponse = resultResponse
                } else {
                    val olArticles = newsListResponse?.articles
                    val newArticles = resultResponse.articles
                    olArticles?.addAll(newArticles)
                }
                return Resource.Success(newsListResponse ?: resultResponse)
            }
        }
        return Resource.Error(response.message())
    }


    fun BookmarkArticle(article: Article) = viewModelScope.launch {
        newsRepository.upsert(article)
    }

    fun getBookmarkedNews() = newsRepository.getBookmarkedNews()

    fun deleteArticle(article: Article) = viewModelScope.launch {
        newsRepository.deleteArticle(article)
    }


}