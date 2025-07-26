package com.luisfagundes.dictionary.data.mapper

import com.luisfagundes.dictionary.data.model.response.TranslationResponse
import com.luisfagundes.dictionary.domain.model.Translation
import javax.inject.Inject

internal class TranslationMapper @Inject constructor(
    private val exampleMapper: ExampleMapper,
) {
    fun mapToDomain(source: TranslationResponse) = Translation(
        featured = source.featured,
        text = source.text,
        partOfSpeech = source.pos,
        examples = source.examples?.map { exampleMapper.mapToDomain(it) } ?: emptyList(),
        usageFrequency = source.usageFrequency.orEmpty()
    )
}