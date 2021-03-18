package com.example.domain.interactor

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.example.domain.entity.Article
import com.example.domain.repository.BreakingNewsRepository
import com.example.domain.repository.SearchNewsRepository
import javax.inject.Inject

class SearchNewsUseCase @Inject constructor(private val searchNewsRepository: SearchNewsRepository):
    UseCaseWithParams<String ,LiveData<PagingData<Article>>>(){

    override suspend fun buildUseCase(params: String): LiveData<PagingData<Article>> {
        return searchNewsRepository.searchNews(params)
    }
}