package com.example.vix_schoters_dicksenalfersiusnovian.ui.fragments

import android.os.Bundle
import android.view.View
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.vix_schoters_dicksenalfersiusnovian.ui.MainActivity
import com.example.vix_schoters_dicksenalfersiusnovian.R
import com.example.vix_schoters_dicksenalfersiusnovian.ui.NewsViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_news.*

class NewsFragment : Fragment(R.layout.fragment_news) {
    lateinit var viewModel: NewsViewModel
    val args: NewsFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel

        val article = args.article
        newsWebView.apply {
            webViewClient = WebViewClient()
            loadUrl(article.url)
        }

        fabBookmark.setOnClickListener {
            viewModel.BookmarkArticle(article)
            Snackbar.make(view, "News Saved Successfully", Snackbar.LENGTH_SHORT).show()
        }
    }
}