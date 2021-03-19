package com.example.domain.repository

import com.example.domain.entity.Article

interface ArticleNewsRepository {

    suspend fun upsertArticle(article: Article): Long
}