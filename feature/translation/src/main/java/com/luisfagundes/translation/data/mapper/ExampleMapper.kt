package com.luisfagundes.translation.data.mapper

import com.luisfagundes.common.mapper.Mapper
import com.luisfagundes.translation.data.model.response.ExampleResponse
import com.luisfagundes.translation.domain.model.Example
import javax.inject.Inject

internal class ExampleMapper @Inject constructor() : Mapper<ExampleResponse, Example> {
    override fun map(source: ExampleResponse) = Example(
        sourceLanguage = source.src,
        targetLanguage = source.dst
    )
}