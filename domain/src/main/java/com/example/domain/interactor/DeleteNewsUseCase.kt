package com.example.domain.interactor

import com.example.domain.entity.Article
import com.example.domain.repository.SavedNewsRepository
import javax.inject.Inject

class DeleteNewsUseCase @Inject constructor(
    private val savedNewsRepository: SavedNewsRepository
): UseCaseWithParams<Article ,Unit>() {

    override suspend fun buildUseCase(params: Article) {
        savedNewsRepository.deleteArticle(params)
    }
}