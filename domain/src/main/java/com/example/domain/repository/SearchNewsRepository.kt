package com.example.domain.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.example.domain.entity.Article

interface SearchNewsRepository {

    suspend fun searchNews(query: String): LiveData<PagingData<Article>>

}