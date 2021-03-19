package com.example.data.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.domain.entity.Source

@Entity(tableName = "articles")
data class ArticleEntity(
    val author: String,
    val content: String,
    val description: String,
    val publishedAt: String,
    val source: Source,
    val title: String,
    val url: String,
    val urlToImage: String,
    @PrimaryKey(autoGenerate = true)
    val id: Long? = null
)