package com.example.newsappcleanarch.ui.`break`

import androidx.lifecycle.ViewModel
import com.example.domain.interactor.BreakingNewsUseCase
import com.example.domain.repository.BreakingNewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BreakingNewsViewModel @Inject constructor(
    private val useCase: BreakingNewsUseCase
): ViewModel() {



}