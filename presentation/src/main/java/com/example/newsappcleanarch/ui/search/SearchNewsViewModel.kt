package com.example.newsappcleanarch.ui.search

import androidx.lifecycle.ViewModel
import com.example.domain.interactor.SearchNewsUseCase
import com.example.domain.repository.SearchNewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchNewsViewModel @Inject constructor(
    private val userCase: SearchNewsUseCase
): ViewModel() {

}