package com.luisfagundes.dictionary.di

import com.luisfagundes.dictionary.data.repository.WordRepositoryImpl
import com.luisfagundes.dictionary.domain.repository.WordRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class RepositoryModule {

    @Binds
    abstract fun bindTranslationRepository(
        impl: WordRepositoryImpl
    ): WordRepository
}