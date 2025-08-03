package com.luisfagundes.dictionary.domain.repository

import com.luisfagundes.dictionary.domain.model.LanguagePair
import com.luisfagundes.dictionary.domain.model.SupportedLanguage
import kotlinx.coroutines.flow.Flow

internal interface LanguagePairRepository {
    suspend fun saveSourceLanguage(language: SupportedLanguage)
    suspend fun saveTargetLanguage(language: SupportedLanguage)
    fun getLanguagePair(): Flow<LanguagePair>
}