package com.luisfagundes.dictionary.di

import com.luisfagundes.dictionary.data.datasource.TranslationDataSource
import com.luisfagundes.dictionary.data.datasource.TranslationDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class DataSourceModule {
    @Binds
    abstract fun provideTranslationDataSourceImpl(
        impl: TranslationDataSourceImpl
    ): TranslationDataSource
}