package com.luisfagundes.dictionary.di

import android.content.Context
import com.luisfagundes.dictionary.data.datastore.LanguagePairDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object DataStoreModule {

    @Provides
    @Singleton
    fun provideLanguagePairDataStore(
        @ApplicationContext context: Context
    ): LanguagePairDataStore = LanguagePairDataStore(context)
}