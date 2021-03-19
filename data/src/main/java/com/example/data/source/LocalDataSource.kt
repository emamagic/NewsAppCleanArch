package com.example.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.example.data.db.NewsDao
import com.example.data.mapper.ArticleMapper
import com.example.domain.entity.Article
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val newsDao: NewsDao,
    private val articleMapper: ArticleMapper
) {

    suspend fun upsertArticle(article: Article): Long = newsDao.upsert(articleMapper.mapFromEntity(article))

    suspend fun deleteArticle(article: Article) = newsDao.deleteArticle(articleMapper.mapFromEntity(article))

    fun getAllArticle(): LiveData<List<Article>> = newsDao.getAllArticles().map { articleMapper.mapToEntityList(it) }

}