package com.luisfagundes.translation.domain.repository

import com.luisfagundes.translation.domain.model.LanguagePair
import com.luisfagundes.translation.domain.model.SupportedLanguage
import kotlinx.coroutines.flow.Flow

internal interface LanguagePairRepository {
    suspend fun saveSourceLanguage(language: SupportedLanguage)
    suspend fun saveTargetLanguage(language: SupportedLanguage)
    fun getLanguagePair(): Flow<LanguagePair>
}