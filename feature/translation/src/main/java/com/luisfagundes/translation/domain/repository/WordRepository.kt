package com.luisfagundes.translation.domain.repository

import com.luisfagundes.translation.domain.model.SaveWordParams
import com.luisfagundes.translation.domain.model.WordHistory
import com.luisfagundes.translation.domain.model.TranslationParams
import com.luisfagundes.translation.domain.model.Word
import kotlinx.coroutines.flow.Flow

internal interface WordRepository {
    fun getTranslations(params: TranslationParams): Flow<List<Word>>
    
    fun getHistory(): Flow<List<WordHistory>>
    
    suspend fun saveWordToHistory(params: SaveWordParams)
    
    suspend fun deleteWordFromHistory(id: Long)
    
    suspend fun clearAllHistory()
}