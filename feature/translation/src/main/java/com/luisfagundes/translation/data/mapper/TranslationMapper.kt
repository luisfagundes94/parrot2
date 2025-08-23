package com.luisfagundes.translation.data.mapper

import com.luisfagundes.translation.data.model.response.TranslationResponse
import com.luisfagundes.translation.domain.model.Translation
import javax.inject.Inject

internal class TranslationMapper @Inject constructor(
    private val exampleMapper: ExampleMapper,
) {
    fun toDomain(source: TranslationResponse) = Translation(
        featured = source.featured,
        text = source.text,
        partOfSpeech = source.pos,
        examples = source.examples?.map { exampleMapper.toDomain(it) } ?: emptyList(),
        usageFrequency = source.usageFrequency.orEmpty()
    )
}