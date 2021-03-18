package com.example.newsappcleanarch.ui.breaking

import androidx.lifecycle.*
import androidx.paging.cachedIn
import com.example.domain.interactor.BreakingNewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

@HiltViewModel
class BreakingNewsViewModel @Inject constructor(
    private val useCase: BreakingNewsUseCase
) : ViewModel() {


    private val currentCountryCode = MutableStateFlow("us")
    val breakingNewsList = currentCountryCode.flatMapLatest { countryCode ->
        useCase.execute(countryCode)
    }.asLiveData(viewModelScope.coroutineContext).cachedIn(viewModelScope)

    fun setCountryCode(countryCode: String) {
        currentCountryCode.value = countryCode
    }

}