package com.luisfagundes.dictionary.data.repository

import com.luisfagundes.dictionary.data.datasource.TranslationDataSource
import com.luisfagundes.dictionary.data.datasource.local.LocalTranslationDataSource
import com.luisfagundes.dictionary.data.mapper.TranslationHistoryMapper
import com.luisfagundes.dictionary.data.mapper.WordMapper
import com.luisfagundes.dictionary.data.model.request.TranslationRequest
import com.luisfagundes.dictionary.domain.model.SupportedLanguage
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
    private val mapper: WordMapper,
    private val historyMapper: TranslationHistoryMapper,
) : TranslationRepository {

    override fun getTranslations(params: TranslationParams): Flow<List<Word>> {
        val request = TranslationRequest(
            query = params.query,
            src = params.sourceLanguage,
            dst = params.targetLanguage
        )
        return dataSource.getTranslations(request).map { wordResponseList ->
            wordResponseList.map { response ->
                mapper.map(response)
            }
        }
    }
    
    override fun getTranslationHistory(): Flow<List<TranslationHistoryItem>> {
        return localDataSource.getAllHistory().map { entities ->
            entities.map { entity ->
                historyMapper.map(entity)
            }
        }
    }
    
    override suspend fun saveTranslationToHistory(
        query: String,
        sourceLanguage: SupportedLanguage,
        targetLanguage: SupportedLanguage,
        translatedText: String,
        partOfSpeech: String,
        timestamp: Long
    ) {
        val entity = historyMapper.mapToEntity(
            query = query,
            sourceLanguage = sourceLanguage,
            targetLanguage = targetLanguage,
            translatedText = translatedText,
            partOfSpeech = partOfSpeech,
            timestamp = timestamp
        )
        localDataSource.insertTranslation(entity)
    }
    
    override suspend fun deleteTranslationHistory(id: Long) {
        localDataSource.deleteTranslationById(id)
    }
    
    override suspend fun clearAllHistory() {
        localDataSource.deleteAllHistory()
    }
}