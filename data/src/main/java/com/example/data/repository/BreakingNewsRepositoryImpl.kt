package com.example.data.repository

import androidx.paging.PagingData
import com.example.data.source.RemoteDataSource
import com.example.domain.entity.Article
import com.example.domain.repository.BreakingNewsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BreakingNewsRepositoryImpl @Inject constructor(
    private val remote: RemoteDataSource
): BreakingNewsRepository {

    override suspend fun getBreakingNews(countryCode: String): Flow<PagingData<Article>> {
       return remote.getArticles(countryCode)
    }

}