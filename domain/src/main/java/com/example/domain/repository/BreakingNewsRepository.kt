package com.example.domain.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.example.domain.entity.Article

interface BreakingNewsRepository {

    suspend fun getBreakingNews(countryCode: String): LiveData<PagingData<Article>>

}