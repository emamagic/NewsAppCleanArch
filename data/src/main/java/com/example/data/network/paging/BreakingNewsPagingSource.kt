package com.example.data.network.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.data.network.NewsApi
import com.example.data.util.Constants.PAGING_START_PAGE
import com.example.domain.entity.Article
import retrofit2.HttpException
import java.io.IOException

class BreakingNewsPagingSource(
    private val newsApi: NewsApi,
    private val countryCode: String
): PagingSource<Int, Article>() {

    override fun getRefreshKey(state: PagingState<Int, Article>) = state.anchorPosition

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        val position = params.key ?: PAGING_START_PAGE
        return try {
            val response = newsApi.getBreakingNews(countryCode ,position ,params.loadSize)
            val article = response.articles

            LoadResult.Page(
                data = article,
                prevKey = if (position == PAGING_START_PAGE) null else position - 1,
                nextKey = if (article.isEmpty()) null else position + 1
            )
        }catch (exception: IOException){
            LoadResult.Error(exception)
        }catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }
}
