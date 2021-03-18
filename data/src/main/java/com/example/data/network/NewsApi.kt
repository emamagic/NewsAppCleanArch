package com.example.data.network

import com.example.data.util.Constants.API_KEY
import com.example.domain.entity.News
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

   @GET("v2/top-headlines")
   suspend fun getBreakingNews(
       @Query("country") countryCode: String,
       @Query("page") page: Int,
       @Query("pageSize") num: Int,
       @Query("apiKey") apiKey: String = API_KEY
   ): News

    @GET("v2/everything")
    suspend fun searchForNews(
        @Query("q") searchQuery: String,
        @Query("page") page: Int,
        @Query("pageSize") num: Int,
        @Query("apiKey") apiKey: String = API_KEY
    ): News

}