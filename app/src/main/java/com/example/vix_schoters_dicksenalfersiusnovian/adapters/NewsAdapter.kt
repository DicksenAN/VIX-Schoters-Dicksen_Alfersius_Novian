package com.example.vix_schoters_dicksenalfersiusnovian.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.vix_schoters_dicksenalfersiusnovian.R
import com.example.vix_schoters_dicksenalfersiusnovian.models.Article
import kotlinx.android.synthetic.main.news_item.view.*

class NewsAdapter : RecyclerView.Adapter<NewsAdapter.NewsArticleViewHolder>() {
    inner class NewsArticleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)


    //To know whether articles are the same or not
    private val differCallback = object : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }
    }

    val isDifferent = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsArticleViewHolder {
        return NewsArticleViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.news_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: NewsArticleViewHolder, position: Int) {
        val article = isDifferent.currentList[position]

        holder.itemView.apply {
            Glide.with(this).load(article.urlToImage).into(ivNewsImage)
            tvNewsSource.text = article.source?.name
            tvNewsTitle.text = article.title
            tvPublishDate.text = article.publishedAt
            tvDescription.text = article.description
            setOnClickListener {
                onItemClickListener?.let { it(article) }
            }
        }
    }

    override fun getItemCount(): Int {
        return isDifferent.currentList.size
    }

    private var onItemClickListener: ((Article) -> Unit)? = null

    fun setOnItemClickListener(listener: (Article) -> Unit) {
        onItemClickListener = listener
    }

}