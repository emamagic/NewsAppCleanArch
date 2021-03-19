package com.example.newsappcleanarch.ui.article

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entity.Article
import com.example.domain.interactor.UpsertNewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArticleNewsViewModel @Inject constructor(
    private val useCase: UpsertNewsUseCase
): ViewModel() {

    fun upsertArticle(article: Article) = viewModelScope.launch {
       useCase.execute(article)
    }


}