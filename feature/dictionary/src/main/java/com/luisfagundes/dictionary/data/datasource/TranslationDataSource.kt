package com.luisfagundes.dictionary.data.datasource

import com.luisfagundes.dictionary.data.model.request.TranslationRequest
import com.luisfagundes.dictionary.data.model.response.WordResponse
import kotlinx.coroutines.flow.Flow

internal interface TranslationDataSource {
    fun getTranslations(request: TranslationRequest): Flow<List<WordResponse>>
}