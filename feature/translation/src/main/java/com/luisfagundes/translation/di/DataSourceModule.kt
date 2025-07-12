package com.luisfagundes.translation.di

import com.luisfagundes.translation.data.datasource.TranslationDataSource
import com.luisfagundes.translation.data.datasource.TranslationDataSourceImpl
import com.luisfagundes.translation.data.network.DeepLApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object DataSourceModule {
    @Provides
    @Singleton
    fun provideTranslationDataSource(
        apiService: DeepLApiService
    ): TranslationDataSource {
        return TranslationDataSourceImpl(apiService)
    }
}