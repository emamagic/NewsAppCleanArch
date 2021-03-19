package com.example.data.repository

import androidx.lifecycle.LiveData
import com.example.data.source.LocalDataSource
import com.example.domain.entity.Article
import com.example.domain.repository.SavedNewsRepository
import javax.inject.Inject

class SavedNewsRepositoryImpl @Inject constructor(
    private val local: LocalDataSource
): SavedNewsRepository {


    override suspend fun deleteArticle(article: Article) {
        local.deleteArticle(article)
    }

    override fun getAllArticles(): LiveData<List<Article>> {
        return local.getAllArticle()
    }


}