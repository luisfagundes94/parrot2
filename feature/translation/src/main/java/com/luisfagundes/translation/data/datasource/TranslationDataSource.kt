package com.luisfagundes.translation.data.datasource

import com.luisfagundes.translation.data.model.response.LanguageListResponse
import com.luisfagundes.translation.data.model.response.TranslationListResponse
import com.luisfagundes.translation.domain.model.TranslationParams
import kotlinx.coroutines.flow.Flow

interface TranslationDataSource {
    fun translateText(params: TranslationParams): Flow<TranslationListResponse>
    fun getSourceLanguageList(): Flow<LanguageListResponse>
    fun getTargetLanguageList(): Flow<LanguageListResponse>
}