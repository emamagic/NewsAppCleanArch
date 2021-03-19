package com.example.domain.interactor

import com.example.domain.entity.Article
import com.example.domain.repository.ArticleNewsRepository
import javax.inject.Inject

class UpsertNewsUseCase @Inject constructor(
    private val articleNewsRepository: ArticleNewsRepository
): UseCaseWithParams<Article,Long>() {

    override suspend fun buildUseCase(params: Article): Long {
        return articleNewsRepository.upsertArticle(params)
    }
}