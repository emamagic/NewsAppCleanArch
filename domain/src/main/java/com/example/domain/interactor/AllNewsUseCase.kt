package com.example.domain.interactor

import androidx.lifecycle.LiveData
import com.example.domain.entity.Article
import com.example.domain.repository.SavedNewsRepository
import javax.inject.Inject

class AllNewsUseCase @Inject constructor(
    private val savedNewsRepository: SavedNewsRepository
): UseCaseImmediate<LiveData<List<Article>>>() {

    override fun buildUseCaseImmediate(): LiveData<List<Article>> {
        return savedNewsRepository.getAllArticles()
    }
}