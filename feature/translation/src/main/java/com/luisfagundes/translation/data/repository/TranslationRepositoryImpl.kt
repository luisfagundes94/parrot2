package com.luisfagundes.translation.data.repository

import com.luisfagundes.translation.data.datasource.TranslationDataSource
import com.luisfagundes.translation.data.mapper.WordMapper
import com.luisfagundes.translation.data.model.request.TranslationRequest
import com.luisfagundes.translation.domain.model.TranslationParams
import com.luisfagundes.translation.domain.model.Word
import com.luisfagundes.translation.domain.repository.TranslationRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal class TranslationRepositoryImpl @Inject constructor(
    private val dataSource: TranslationDataSource,
    private val mapper: WordMapper,
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
}