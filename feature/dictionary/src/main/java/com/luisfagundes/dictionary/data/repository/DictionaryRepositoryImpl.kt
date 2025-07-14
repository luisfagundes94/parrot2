package com.luisfagundes.dictionary.data.repository

import com.luisfagundes.dictionary.data.datasource.DictionaryDataSource
import com.luisfagundes.dictionary.data.mapper.WordMapper
import com.luisfagundes.dictionary.data.model.response.WordResponse
import com.luisfagundes.dictionary.data.model.request.TranslationRequest
import com.luisfagundes.dictionary.domain.model.TranslationParams
import com.luisfagundes.dictionary.domain.model.Word
import com.luisfagundes.dictionary.domain.repository.DictionaryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal class DictionaryRepositoryImpl @Inject constructor(
    private val dataSource: DictionaryDataSource,
    private val mapper: WordMapper,
) : DictionaryRepository {

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