package com.example.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.data.db.model.ArticleEntity
import com.example.domain.entity.Article

@Dao
interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(article: ArticleEntity): Long

    @Query("SELECT * FROM articles ")
    fun getAllArticles(): LiveData<List<ArticleEntity>>

    @Delete
    suspend fun deleteArticle(article: ArticleEntity)

}