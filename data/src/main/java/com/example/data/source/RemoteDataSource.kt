package com.example.data.source

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.example.data.network.NewsApi
import com.example.data.network.paging.BreakingNewsPagingSource
import com.example.data.network.paging.SearchNewsPagingSource
import com.example.data.network.safe.SafeApi
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val newsApi: NewsApi
): SafeApi() {

    fun searchArticles(query: String) =
        Pager(
            config = PagingConfig(
                pageSize = 20,
                maxSize = 100,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { SearchNewsPagingSource(newsApi ,query) }
        ).flow

    fun getArticles(countryCode: String) =
        Pager(
            config = PagingConfig(
                pageSize = 20,
                maxSize = 100,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { BreakingNewsPagingSource(newsApi ,countryCode) }
        ).flow

}
