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
import com.luisfagundes.translation.domain.repository.TranslationRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal class TranslationRepositoryImpl @Inject constructor(
    private val dataSource: TranslationDataSource,
    private val localDataSource: LocalTranslationDataSource,
    private val wordMapper: WordMapper,
    private val wordHistoryMapper: WordHistoryMapper
) : TranslationRepository {

    override fun translate(params: TranslationParams): Flow<List<Word>> {
        val request = TranslationRequest(
            query = params.query,
            src = params.sourceLanguage,
            dst = params.targetLanguage
        )
        return dataSource.translateText(request).map { wordResponseList ->
            wordResponseList.map { response ->
                wordMapper.toDomain(response)
            }
        }
    }
    
    override fun getSavedTranslations(): Flow<List<WordHistory>> {
        return localDataSource.getAllSavedTranslations().map { entities ->
            entities.map { entity ->
                wordHistoryMapper.toDomain(entity)
            }
        }
    }
    
    override suspend fun saveWord(params: SaveWordParams) {
        val entity = wordHistoryMapper.toEntity(params)
        localDataSource.insertTranslation(entity)
    }
    
    override suspend fun deleteSavedWord(id: Long) {
        localDataSource.deleteTranslationById(id)
    }
    
    override suspend fun clearAllSavedWords() {
        localDataSource.deleteAllTranslationHistory()
    }
}