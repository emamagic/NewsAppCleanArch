package com.example.domain.interactor

import androidx.paging.PagingData
import com.example.domain.entity.Article
import com.example.domain.repository.BreakingNewsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BreakingNewsUseCase @Inject constructor(private val breakingNewsRepository: BreakingNewsRepository):
    UseCaseWithParams<String, Flow<PagingData<Article>>>() {

    override suspend fun buildUseCase(params: String): Flow<PagingData<Article>> {
        return breakingNewsRepository.getBreakingNews(params)
    }
}