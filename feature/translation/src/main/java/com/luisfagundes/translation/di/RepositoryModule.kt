package com.luisfagundes.translation.di

import com.luisfagundes.translation.data.repository.LanguagePairRepositoryImpl
import com.luisfagundes.translation.data.repository.TranslationRepositoryImpl
import com.luisfagundes.translation.domain.repository.LanguagePairRepository
import com.luisfagundes.translation.domain.repository.TranslationRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class RepositoryModule {

    @Binds
    abstract fun bindTranslationRepository(
        impl: TranslationRepositoryImpl
    ): TranslationRepository

    @Binds
    abstract fun bindLanguagePairRepository(
        impl: LanguagePairRepositoryImpl
    ): LanguagePairRepository
}