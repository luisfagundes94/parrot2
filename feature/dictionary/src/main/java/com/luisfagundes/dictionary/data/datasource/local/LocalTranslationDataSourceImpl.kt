package com.luisfagundes.dictionary.data.datasource.local

import com.luisfagundes.dictionary.data.database.dao.TranslationHistoryDao
import com.luisfagundes.dictionary.data.database.entities.TranslationHistoryEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

internal class LocalTranslationDataSourceImpl @Inject constructor(
    private val dao: TranslationHistoryDao
) : LocalTranslationDataSource {
    
    override fun getAllHistory(): Flow<List<TranslationHistoryEntity>> {
        return dao.getAllHistory()
    }
    
    override suspend fun insertTranslation(translation: TranslationHistoryEntity) {
        dao.insertTranslation(translation)
    }
    
    override suspend fun deleteTranslationById(id: Long) {
        dao.deleteTranslationById(id)
    }
    
    override suspend fun deleteAllHistory() {
        dao.deleteAllHistory()
    }
}