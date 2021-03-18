package com.example.domain.interactor

import androidx.paging.PagingData
import com.example.domain.entity.Article
import com.example.domain.repository.SearchNewsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchNewsUseCase @Inject constructor(private val searchNewsRepository: SearchNewsRepository):
    UseCaseWithParams<String ,Flow<PagingData<Article>>>(){

    override suspend fun buildUseCase(params: String): Flow<PagingData<Article>> {
        return searchNewsRepository.searchNews(params)
    }
}