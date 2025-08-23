package com.luisfagundes.translation.data.datasource.local

import com.luisfagundes.translation.data.database.dao.TranslationHistoryDao
import com.luisfagundes.translation.data.database.entities.SavedTranslationEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

internal class LocalTranslationDataSourceImpl @Inject constructor(
    private val dao: TranslationHistoryDao
) : LocalTranslationDataSource {
    
    override fun getAllSavedTranslations(): Flow<List<SavedTranslationEntity>> {
        return dao.getAllHistory()
    }
    
    override suspend fun insertTranslation(translation: SavedTranslationEntity) {
        dao.insertTranslation(translation)
    }
    
    override suspend fun deleteTranslationById(id: Long) {
        dao.deleteTranslationById(id)
    }
    
    override suspend fun deleteAllTranslationHistory() {
        dao.deleteAllHistory()
    }
}