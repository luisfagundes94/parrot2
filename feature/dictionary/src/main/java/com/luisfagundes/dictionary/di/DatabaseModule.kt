package com.luisfagundes.dictionary.di

import android.content.Context
import androidx.room.Room
import com.luisfagundes.dictionary.data.database.ParrotDatabase
import com.luisfagundes.dictionary.data.database.dao.TranslationHistoryDao
import com.luisfagundes.dictionary.data.datasource.local.LocalTranslationDataSource
import com.luisfagundes.dictionary.data.datasource.local.LocalTranslationDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object DatabaseModule {
    
    @Provides
    @Singleton
    fun provideParrotDatabase(@ApplicationContext context: Context): ParrotDatabase {
        return Room.databaseBuilder(
            context,
            ParrotDatabase::class.java,
            ParrotDatabase.DATABASE_NAME
        ).build()
    }
    
    @Provides
    fun provideTranslationHistoryDao(database: ParrotDatabase): TranslationHistoryDao {
        return database.translationHistoryDao()
    }
    
    @Provides
    fun provideLocalTranslationDataSource(
        dao: TranslationHistoryDao
    ): LocalTranslationDataSource {
        return LocalTranslationDataSourceImpl(dao)
    }
}