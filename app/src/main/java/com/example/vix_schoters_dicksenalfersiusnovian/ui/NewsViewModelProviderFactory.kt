package com.example.vix_schoters_dicksenalfersiusnovian.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.vix_schoters_dicksenalfersiusnovian.repository.NewsRepository

class NewsViewModelProviderFactory(
    val newsRepository: NewsRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NewsViewModel(newsRepository) as T
    }
}