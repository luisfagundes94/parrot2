package com.luisfagundes.translation.data.mapper

import com.luisfagundes.translation.data.model.response.AudioLinkResponse
import com.luisfagundes.translation.domain.model.AudioLink
import javax.inject.Inject

internal class AudioLinkMapper @Inject constructor() {
    fun toDomain(source: AudioLinkResponse) = AudioLink(
        url = source.url,
        type = source.type.orEmpty()
    )
}