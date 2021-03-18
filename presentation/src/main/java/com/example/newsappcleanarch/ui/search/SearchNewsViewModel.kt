package com.example.newsappcleanarch.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.domain.interactor.SearchNewsUseCase
import com.example.domain.repository.SearchNewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

@HiltViewModel
class SearchNewsViewModel @Inject constructor(
    private val useCase: SearchNewsUseCase
): ViewModel() {

    private val currentQuery = MutableStateFlow("")
    val searchNews =  currentQuery.flatMapLatest { searchQuery ->
        useCase.execute(searchQuery)
    }.asLiveData(viewModelScope.coroutineContext).cachedIn(viewModelScope)

    fun searchQuery(query: String){
        currentQuery.value = query
    }

}