package com.example.newsappcleanarch.ui.save

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entity.Article
import com.example.domain.interactor.AllNewsUseCase
import com.example.domain.interactor.DeleteNewsUseCase
import com.example.domain.interactor.UpsertNewsUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class SaveNewsViewModel @Inject constructor(
    private val allNewsUseCase: AllNewsUseCase,
    private val deleteNewsUseCase: DeleteNewsUseCase,
    private val upsertNewsUseCase: UpsertNewsUseCase
): ViewModel() {

    fun getAllNews() = allNewsUseCase.execute()

    fun deleteNewsArticle(article: Article) = viewModelScope.launch {
        deleteNewsUseCase.execute(article)
    }

    fun upsertNewsArticle(article: Article) = viewModelScope.launch {
        upsertNewsUseCase.execute(article)
    }
}