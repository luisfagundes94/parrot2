package com.luisfagundes.translation.data.datasource

import com.luisfagundes.translation.data.model.request.TranslationRequest
import com.luisfagundes.translation.data.model.response.WordResponse
import kotlinx.coroutines.flow.Flow

internal interface TranslationDataSource {
    fun translateText(request: TranslationRequest): Flow<List<WordResponse>>
}