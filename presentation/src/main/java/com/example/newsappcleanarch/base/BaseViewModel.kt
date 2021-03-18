package com.example.newsappcleanarch.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

open class BaseViewModel: ViewModel(){
    var loadingState: MutableLiveData<Boolean> = MutableLiveData(false)

    inline fun baseViewModelScope(crossinline call: suspend CoroutineScope.() -> Unit): Job =
        viewModelScope.launch {
            loadingState.value = true
            call()
        }
}