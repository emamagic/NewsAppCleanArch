package com.example.domain.repository

import androidx.paging.PagingData
import com.example.domain.entity.Article
import kotlinx.coroutines.flow.Flow

interface BreakingNewsRepository {

    suspend fun getBreakingNews(countryCode: String): Flow<PagingData<Article>>

}