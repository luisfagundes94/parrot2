package com.luisfagundes.dictionary.domain.repository

import com.luisfagundes.dictionary.domain.model.SaveTranslationParams
import com.luisfagundes.dictionary.domain.model.TranslationHistoryItem
import com.luisfagundes.dictionary.domain.model.TranslationParams
import com.luisfagundes.dictionary.domain.model.Word
import kotlinx.coroutines.flow.Flow

internal interface TranslationRepository {
    fun getTranslations(params: TranslationParams): Flow<List<Word>>
    
    fun getTranslationHistory(): Flow<List<TranslationHistoryItem>>
    
    suspend fun saveTranslationToHistory(params: SaveTranslationParams)
    
    suspend fun deleteTranslationHistory(id: Long)
    
    suspend fun clearAllHistory()
}