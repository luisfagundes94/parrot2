package com.luisfagundes.dictionary.di

import com.luisfagundes.dictionary.data.datasource.DictionaryDataSource
import com.luisfagundes.dictionary.data.datasource.DictionaryDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class DataSourceModule {
    @Binds
    abstract fun provideDictionaryDataSourceImpl(
        impl: DictionaryDataSourceImpl
    ): DictionaryDataSource
}