package com.luisfagundes.dictionary.di

import com.luisfagundes.dictionary.data.repository.DictionaryRepositoryImpl
import com.luisfagundes.dictionary.domain.repository.DictionaryRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class RepositoryModule {

    @Binds
    abstract fun bindDictionaryRepository(
        impl: DictionaryRepositoryImpl
    ): DictionaryRepository
}