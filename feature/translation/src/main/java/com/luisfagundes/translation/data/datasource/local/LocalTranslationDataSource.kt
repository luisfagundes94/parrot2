package com.luisfagundes.translation.data.datasource.local

import com.luisfagundes.translation.data.database.entities.TranslationHistoryEntity
import kotlinx.coroutines.flow.Flow

internal interface LocalTranslationDataSource {
    fun getAllHistory(): Flow<List<TranslationHistoryEntity>>
    suspend fun insertTranslation(translation: TranslationHistoryEntity)
    suspend fun deleteTranslationById(id: Long)
    suspend fun deleteAllHistory()
}