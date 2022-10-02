package com.example.vix_schoters_dicksenalfersiusnovian.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable


@Entity(
    tableName = "articles"
)

//To store individual news article
data class Article(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    val author: String?,
    val content: String?,
    val description: String?,
    val publishedAt: String?,
    val source: Source?,
    val title: String?,
    val url: String?,
    val urlToImage: String?
) : Serializable