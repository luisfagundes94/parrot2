package com.luisfagundes.translation.data.datasource.local

import com.luisfagundes.translation.data.database.entities.SavedTranslationEntity
import kotlinx.coroutines.flow.Flow

internal interface LocalTranslationDataSource {
    fun getAllSavedTranslations(): Flow<List<SavedTranslationEntity>>
    suspend fun insertTranslation(translation: SavedTranslationEntity)
    suspend fun deleteTranslationById(id: Long)
    suspend fun deleteAllTranslationHistory()
}