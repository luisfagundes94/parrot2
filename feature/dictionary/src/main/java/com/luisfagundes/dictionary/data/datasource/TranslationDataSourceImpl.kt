package com.luisfagundes.dictionary.data.datasource

import com.luisfagundes.common.util.parseError
import com.luisfagundes.dictionary.data.model.request.TranslationRequest
import com.luisfagundes.dictionary.data.model.response.WordResponse
import com.luisfagundes.dictionary.data.remote.LingueeApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

internal class TranslationDataSourceImpl @Inject constructor(
    private val apiService: LingueeApiService
) : TranslationDataSource {
    override fun getTranslations(request: TranslationRequest): Flow<List<WordResponse>> = flow {
        emit(
            apiService.getTranslations(
                query = request.query,
                src = request.src,
                dst = request.dst
            )
        )
    }.parseError()
}