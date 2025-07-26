package com.luisfagundes.dictionary.data.repository

import com.luisfagundes.dictionary.data.datasource.TranslationDataSource
import com.luisfagundes.dictionary.data.datasource.local.LocalTranslationDataSource
import com.luisfagundes.dictionary.data.mapper.TranslationHistoryMapper
import com.luisfagundes.dictionary.data.mapper.WordMapper
import com.luisfagundes.dictionary.data.model.request.TranslationRequest
import com.luisfagundes.dictionary.domain.model.SaveTranslationParams
import com.luisfagundes.dictionary.domain.model.TranslationHistoryItem
import com.luisfagundes.dictionary.domain.model.TranslationParams
import com.luisfagundes.dictionary.domain.model.Word
import com.luisfagundes.dictionary.domain.repository.TranslationRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal class TranslationRepositoryImpl @Inject constructor(
    private val dataSource: TranslationDataSource,
    private val localDataSource: LocalTranslationDataSource,
    private val wordMapper: WordMapper,
    private val translationHistoryMapper: TranslationHistoryMapper
) : TranslationRepository {

    override fun getTranslations(params: TranslationParams): Flow<List<Word>> {
        val request = TranslationRequest(
            query = params.query,
            src = params.sourceLanguage,
            dst = params.targetLanguage
        )
        return dataSource.getTranslations(request).map { wordResponseList ->
            wordResponseList.map { response ->
                wordMapper.mapToDomain(response)
            }
        }
    }
    
    override fun getTranslationHistory(): Flow<List<TranslationHistoryItem>> {
        return localDataSource.getAllHistory().map { entities ->
            entities.map { entity ->
                translationHistoryMapper.mapToDomain(entity)
            }
        }
    }
    
    override suspend fun saveTranslationToHistory(params: SaveTranslationParams) {
        val entity = translationHistoryMapper.mapToEntity(params)
        localDataSource.insertTranslation(entity)
    }
    
    override suspend fun deleteTranslationHistory(id: Long) {
        localDataSource.deleteTranslationById(id)
    }
    
    override suspend fun clearAllHistory() {
        localDataSource.deleteAllHistory()
    }
}