package com.luisfagundes.dictionary.domain.repository

import com.luisfagundes.dictionary.domain.model.SaveWordParams
import com.luisfagundes.dictionary.domain.model.WordHistory
import com.luisfagundes.dictionary.domain.model.TranslationParams
import com.luisfagundes.dictionary.domain.model.Word
import kotlinx.coroutines.flow.Flow

internal interface WordRepository {
    fun getTranslations(params: TranslationParams): Flow<List<Word>>
    
    fun getHistory(): Flow<List<WordHistory>>
    
    suspend fun saveWordToHistory(params: SaveWordParams)
    
    suspend fun deleteWordFromHistory(id: Long)
    
    suspend fun clearAllHistory()
}