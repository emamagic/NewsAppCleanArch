package com.example.domain.interactor

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.example.domain.entity.Article
import com.example.domain.repository.BreakingNewsRepository
import javax.inject.Inject

class BreakingNewsUseCase @Inject constructor(private val breakingNewsRepository: BreakingNewsRepository):
    UseCaseWithParams<String, LiveData<PagingData<Article>>>() {

    override suspend fun buildUseCase(params: String): LiveData<PagingData<Article>> {
        return breakingNewsRepository.getBreakingNews(params)
    }
}