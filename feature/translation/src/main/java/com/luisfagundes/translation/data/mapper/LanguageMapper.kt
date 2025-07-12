package com.luisfagundes.translation.data.mapper

import com.luisfagundes.common.mapper.Mapper
import com.luisfagundes.translation.data.model.response.LanguageListResponse
import com.luisfagundes.translation.data.model.response.LanguageResponse
import com.luisfagundes.translation.domain.model.Language

class LanguageMapper : Mapper<LanguageListResponse, List<Language>> {
    override fun map(source: LanguageListResponse): List<Language> {
        return source.languages.map { response -> response.mapToLanguage() }
    }

    private fun LanguageResponse.mapToLanguage() = Language(
        code = this.language,
        name = this.name,
        supportsFormality = this.supportsFormality ?: false
    )
}