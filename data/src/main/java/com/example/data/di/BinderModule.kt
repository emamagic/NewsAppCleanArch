package com.example.data.di

import com.example.data.repository.BreakingNewsRepositoryImpl
import com.example.data.repository.SearchNewsRepositoryImpl
import com.example.domain.repository.BreakingNewsRepository
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

}