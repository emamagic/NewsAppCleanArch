package com.example.domain.repository

import androidx.lifecycle.LiveData
import com.example.domain.entity.Article

interface SavedNewsRepository {

    suspend fun deleteArticle(article: Article)

    fun getAllArticles(): LiveData<List<Article>>

}