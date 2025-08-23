package com.luisfagundes.translation.di

import com.luisfagundes.translation.data.repository.LanguagePairRepositoryImpl
import com.luisfagundes.translation.data.repository.WordRepositoryImpl
import com.luisfagundes.translation.domain.repository.LanguagePairRepository
import com.luisfagundes.translation.domain.repository.WordRepository
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

    @Binds
    abstract fun bindLanguagePairRepository(
        impl: LanguagePairRepositoryImpl
    ): LanguagePairRepository
}