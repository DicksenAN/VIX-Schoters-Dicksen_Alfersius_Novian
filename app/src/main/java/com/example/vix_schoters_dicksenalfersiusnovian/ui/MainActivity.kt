package com.example.vix_schoters_dicksenalfersiusnovian.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.vix_schoters_dicksenalfersiusnovian.R
import kotlinx.android.synthetic.main.activity_main.*
import com.example.vix_schoters_dicksenalfersiusnovian.database.ArticleDatabase
import com.example.vix_schoters_dicksenalfersiusnovian.repository.NewsRepository

class MainActivity : AppCompatActivity() {
    lateinit var viewModel: NewsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupActionBar()

        val newsRepository = NewsRepository(ArticleDatabase(this))
        val viewModelProviderFactory = NewsViewModelProviderFactory(newsRepository)
        viewModel = ViewModelProvider(this, viewModelProviderFactory).get(NewsViewModel::class.java)
        btnNavigationView.setupWithNavController(mainActivityFragment.findNavController())

    }

    fun setupActionBar(){
        actionBar?.setTitle("API News App")
        supportActionBar?.setTitle("API News App")
    }
}