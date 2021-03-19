package com.example.data.repository

import com.example.data.source.LocalDataSource
import com.example.domain.entity.Article
import com.example.domain.repository.ArticleNewsRepository
import javax.inject.Inject

class ArticleNewsRepositoryImpl @Inject constructor(
    private val local: LocalDataSource
): ArticleNewsRepository {

    override suspend fun upsertArticle(article: Article): Long {
        return local.upsertArticle(article)
    }


}