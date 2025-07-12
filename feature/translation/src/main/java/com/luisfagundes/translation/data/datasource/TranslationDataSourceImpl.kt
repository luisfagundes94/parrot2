package com.luisfagundes.translation.data.datasource

import com.luisfagundes.common.util.parseError
import com.luisfagundes.translation.data.model.request.TranslationRequest
import com.luisfagundes.translation.data.model.response.LanguageListResponse
import com.luisfagundes.translation.data.model.response.TranslationListResponse
import com.luisfagundes.translation.data.network.DeepLApiService
import com.luisfagundes.translation.domain.model.TranslationParams
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class TranslationDataSourceImpl @Inject constructor(
    private val apiService: DeepLApiService
) : TranslationDataSource {
    override fun translateText(params: TranslationParams): Flow<TranslationListResponse> = flow {
        emit(
            apiService.translate(
                request = TranslationRequest(
                    text = params.text,
                    sourceLang = params.sourceLanguage,
                    targetLang = params.targetLanguage,
                    formality = params.formality,
                    preserveFormatting = params.preserveFormatting
                )
            )
        )
    }.parseError()

    override fun getSourceLanguageList(): Flow<LanguageListResponse> = flow {
        emit(apiService.getSourceLanguages())
    }.parseError()

    override fun getTargetLanguageList(): Flow<LanguageListResponse> = flow {
        emit(apiService.getTargetLanguages())
    }.parseError()
}