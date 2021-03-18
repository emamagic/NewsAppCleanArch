package com.example.data.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.data.db.NewsDao
import com.example.data.db.NewsDatabase
import com.example.data.util.Constants.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Provides
    @Singleton
    fun provideRoomDatabase(@ApplicationContext context: Context): NewsDatabase{
        return Room.databaseBuilder(context ,NewsDatabase::class.java ,DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideNewsDao(newsDatabase: NewsDatabase): NewsDao{
        return newsDatabase.getNewsDao()
    }
}