package com.luisfagundes.translation.data.repository

import com.luisfagundes.translation.data.datasource.TranslationDataSource
import com.luisfagundes.translation.data.mapper.LanguageMapper
import com.luisfagundes.translation.data.mapper.TranslationMapper
import com.luisfagundes.translation.domain.model.Language
import com.luisfagundes.translation.domain.model.Translation
import com.luisfagundes.translation.domain.model.TranslationParams
import com.luisfagundes.translation.domain.repository.TranslationRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TranslationRepositoryImpl @Inject constructor(
    private val dataSource: TranslationDataSource,
    private val translationMapper: TranslationMapper,
    private val languageMapper: LanguageMapper,
) : TranslationRepository {

    override fun translateText(
        params: TranslationParams
    ): Flow<List<Translation>> {
        return dataSource.translateText(params).map { response ->
            translationMapper.map(response)
        }
    }

    override fun getSourceLanguages(): Flow<List<Language>> {
        return dataSource.getSourceLanguageList().map { response ->
            languageMapper.map(response)
        }
    }

    override fun getTargetLanguages(): Flow<List<Language>> {
        return dataSource.getTargetLanguageList().map { response ->
            languageMapper.map(response)
        }
    }
}