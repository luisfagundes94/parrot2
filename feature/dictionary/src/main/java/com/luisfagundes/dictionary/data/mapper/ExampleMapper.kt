package com.luisfagundes.dictionary.data.mapper

import com.luisfagundes.common.mapper.Mapper
import com.luisfagundes.dictionary.data.model.response.ExampleResponse
import com.luisfagundes.dictionary.domain.model.Example
import javax.inject.Inject

internal class ExampleMapper @Inject constructor() : Mapper<ExampleResponse, Example> {
    override fun map(source: ExampleResponse) = Example(
        sourceLanguage = source.src,
        targetLanguage = source.dst
    )
}