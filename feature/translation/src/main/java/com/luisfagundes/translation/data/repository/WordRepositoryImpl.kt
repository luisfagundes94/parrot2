package com.luisfagundes.translation.data.repository

import com.luisfagundes.translation.data.datasource.TranslationDataSource
import com.luisfagundes.translation.data.datasource.local.LocalTranslationDataSource
import com.luisfagundes.translation.data.mapper.WordHistoryMapper
import com.luisfagundes.translation.data.mapper.WordMapper
import com.luisfagundes.translation.data.model.request.TranslationRequest
import com.luisfagundes.translation.domain.model.SaveWordParams
import com.luisfagundes.translation.domain.model.WordHistory
import com.luisfagundes.translation.domain.model.TranslationParams
import com.luisfagundes.translation.domain.model.Word
import com.luisfagundes.translation.domain.repository.WordRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal class WordRepositoryImpl @Inject constructor(
    private val dataSource: TranslationDataSource,
    private val localDataSource: LocalTranslationDataSource,
    private val wordMapper: WordMapper,
    private val wordHistoryMapper: WordHistoryMapper
) : WordRepository {

    override fun getTranslations(params: TranslationParams): Flow<List<Word>> {
        val request = TranslationRequest(
            query = params.query,
            src = params.sourceLanguage,
            dst = params.targetLanguage
        )
        return dataSource.getTranslations(request).map { wordResponseList ->
            wordResponseList.map { response ->
                wordMapper.toDomain(response)
            }
        }
    }
    
    override fun getHistory(): Flow<List<WordHistory>> {
        return localDataSource.getAllHistory().map { entities ->
            entities.map { entity ->
                wordHistoryMapper.toDomain(entity)
            }
        }
    }
    
    override suspend fun saveWordToHistory(params: SaveWordParams) {
        val entity = wordHistoryMapper.toEntity(params)
        localDataSource.insertTranslation(entity)
    }
    
    override suspend fun deleteWordFromHistory(id: Long) {
        localDataSource.deleteTranslationById(id)
    }
    
    override suspend fun clearAllHistory() {
        localDataSource.deleteAllHistory()
    }
}