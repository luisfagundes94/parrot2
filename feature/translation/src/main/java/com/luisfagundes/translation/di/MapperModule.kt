package com.luisfagundes.translation.di

import com.luisfagundes.common.mapper.Mapper
import com.luisfagundes.translation.data.mapper.ApiUsageMapper
import com.luisfagundes.translation.data.mapper.LanguageMapper
import com.luisfagundes.translation.data.mapper.TranslationMapper
import com.luisfagundes.translation.data.model.response.ApiUsageResponse
import com.luisfagundes.translation.data.model.response.LanguageListResponse
import com.luisfagundes.translation.data.model.response.TranslationListResponse
import com.luisfagundes.translation.domain.model.ApiUsage
import com.luisfagundes.translation.domain.model.Language
import com.luisfagundes.translation.domain.model.Translation
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class MapperModule {

    @Binds
    @Singleton
    abstract fun bindTranslationMapper(
        translationMapper: TranslationMapper
    ): Mapper<TranslationListResponse, List<Translation>>

    @Binds
    @Singleton
    abstract fun bindLanguageMapper(
        languageMapper: LanguageMapper
    ): Mapper<LanguageListResponse, List<Language>>

    @Binds
    @Singleton
    abstract fun bindApiUsageMapper(
        apiUsageMapper: ApiUsageMapper
    ): Mapper<ApiUsageResponse, ApiUsage>
}