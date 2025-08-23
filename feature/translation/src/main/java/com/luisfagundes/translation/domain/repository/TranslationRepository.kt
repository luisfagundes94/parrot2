package com.luisfagundes.translation.domain.repository

import com.luisfagundes.translation.domain.model.SaveWordParams
import com.luisfagundes.translation.domain.model.SavedWord
import com.luisfagundes.translation.domain.model.TranslationParams
import com.luisfagundes.translation.domain.model.Word
import kotlinx.coroutines.flow.Flow

internal interface TranslationRepository {
    fun translate(params: TranslationParams): Flow<List<Word>>
    
    fun getSavedWords(): Flow<List<SavedWord>>
    
    suspend fun saveWord(params: SaveWordParams)
    
    suspend fun deleteSavedWord(id: Long)
    
    suspend fun clearAllSavedWords()
}