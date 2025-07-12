package com.luisfagundes.translation.data.mapper

import com.luisfagundes.common.mapper.Mapper
import com.luisfagundes.translation.data.model.response.TranslationListResponse
import com.luisfagundes.translation.data.model.response.TranslationResponse
import com.luisfagundes.translation.domain.model.Translation
import javax.inject.Inject

internal class TranslationMapper @Inject constructor() : Mapper<TranslationListResponse, List<Translation>> {
    override fun map(source: TranslationListResponse): List<Translation> {
        return source.translations.map { response -> response.mapToTranslation() }
    }

    private fun TranslationResponse.mapToTranslation() = Translation(
        translatedText = this.text,
        detectedSourceLanguage = this.detectedSourceLanguage
    )
}