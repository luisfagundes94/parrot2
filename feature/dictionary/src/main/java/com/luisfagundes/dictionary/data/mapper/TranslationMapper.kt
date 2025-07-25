package com.luisfagundes.dictionary.data.mapper

import com.luisfagundes.common.mapper.Mapper
import com.luisfagundes.dictionary.data.model.response.TranslationResponse
import com.luisfagundes.dictionary.domain.model.Translation
import javax.inject.Inject

internal class TranslationMapper @Inject constructor(
    private val exampleMapper: ExampleMapper,
) : Mapper<TranslationResponse, Translation> {
    override fun map(source: TranslationResponse) = Translation(
        featured = source.featured,
        text = source.text,
        partOfSpeech = source.pos,
        examples = source.examples?.map { exampleMapper.map(it) } ?: emptyList(),
        usageFrequency = source.usageFrequency.orEmpty()
    )
}