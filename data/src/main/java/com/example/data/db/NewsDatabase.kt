package com.example.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.data.db.converter.SourceConverter
import com.example.data.db.model.ArticleEntity

@Database(
    entities = [ArticleEntity::class],
    version = 1
)
@TypeConverters(SourceConverter::class)
abstract class NewsDatabase : RoomDatabase() {

    abstract fun getNewsDao(): NewsDao
}