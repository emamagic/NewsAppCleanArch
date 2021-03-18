package com.example.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.example.data.source.RemoteDataSource
import com.example.domain.entity.Article
import com.example.domain.repository.SearchNewsRepository
import javax.inject.Inject

class SearchNewsRepositoryImpl @Inject constructor(
    private val remote: RemoteDataSource
): SearchNewsRepository {

    override suspend fun searchNews(query: String): LiveData<PagingData<Article>> {
        return remote.searchArticles(query)
    }

}