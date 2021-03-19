package com.example.data.di

import com.example.data.repository.ArticleNewsRepositoryImpl
import com.example.data.repository.BreakingNewsRepositoryImpl
import com.example.data.repository.SavedNewsRepositoryImpl
import com.example.data.repository.SearchNewsRepositoryImpl
import com.example.domain.repository.ArticleNewsRepository
import com.example.domain.repository.BreakingNewsRepository
import com.example.domain.repository.SavedNewsRepository
import com.example.domain.repository.SearchNewsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class BinderModule {

    @Binds
    abstract fun bindBreakingNewsRepository(breakingNewsRepositoryImpl: BreakingNewsRepositoryImpl): BreakingNewsRepository

    @Binds
    abstract fun bindSearchNewsRepository(searchNewsRepositoryImpl: SearchNewsRepositoryImpl): SearchNewsRepository

    @Binds
    abstract fun bindArticleNewsRepository(searchNewsRepositoryImpl: ArticleNewsRepositoryImpl): ArticleNewsRepository

    @Binds
    abstract fun bindSavedNewsRepository(searchNewsRepositoryImpl: SavedNewsRepositoryImpl): SavedNewsRepository

}