package com.luisfagundes.translation.data.mapper

import com.luisfagundes.translation.data.model.response.ExampleResponse
import com.luisfagundes.translation.domain.model.Example
import javax.inject.Inject

internal class ExampleMapper @Inject constructor() {
    fun toDomain(source: ExampleResponse) = Example(
        sourceLanguage = source.src,
        targetLanguage = source.dst
    )
}