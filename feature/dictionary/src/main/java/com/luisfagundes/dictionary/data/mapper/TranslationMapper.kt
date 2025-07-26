package com.luisfagundes.dictionary.data.mapper

import com.luisfagundes.dictionary.data.model.response.TranslationResponse
import com.luisfagundes.dictionary.domain.model.Translation
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