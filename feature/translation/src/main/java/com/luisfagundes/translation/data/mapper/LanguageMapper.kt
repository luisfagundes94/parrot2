package com.luisfagundes.translation.data.mapper

import com.luisfagundes.common.mapper.Mapper
import com.luisfagundes.translation.data.model.response.LanguageResponse
import com.luisfagundes.translation.domain.model.Language

class LanguageMapper : Mapper<LanguageResponse, Language> {
    override fun map(source: LanguageResponse): Language {
        return Language(
            code = source.language,
            name = source.name,
            supportsFormality = source.supportsFormality ?: false
        )
    }
}