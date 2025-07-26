package com.luisfagundes.dictionary.data.mapper

import com.luisfagundes.dictionary.data.model.response.AudioLinkResponse
import com.luisfagundes.dictionary.domain.model.AudioLink
import javax.inject.Inject

internal class AudioLinkMapper @Inject constructor() {
    fun mapToDomain(source: AudioLinkResponse) = AudioLink(
        url = source.url,
        type = source.type.orEmpty()
    )
}