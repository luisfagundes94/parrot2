package com.luisfagundes.dictionary.domain.repository

import com.luisfagundes.dictionary.domain.model.SupportedLanguage
import com.luisfagundes.dictionary.domain.model.TranslationHistoryItem
import com.luisfagundes.dictionary.domain.model.TranslationParams
import com.luisfagundes.dictionary.domain.model.Word
import kotlinx.coroutines.flow.Flow

internal interface TranslationRepository {
    fun getTranslations(params: TranslationParams): Flow<List<Word>>
    
    // History operations
    fun getTranslationHistory(): Flow<List<TranslationHistoryItem>>
    
    suspend fun saveTranslationToHistory(
        query: String,
        sourceLanguage: SupportedLanguage,
        targetLanguage: SupportedLanguage,
        translatedText: String,
        partOfSpeech: String,
        timestamp: Long
    )
    
    suspend fun deleteTranslationHistory(id: Long)
    
    suspend fun clearAllHistory()
}