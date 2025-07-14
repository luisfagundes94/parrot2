package com.luisfagundes.dictionary.data.mapper

import com.luisfagundes.common.mapper.Mapper
import com.luisfagundes.dictionary.data.model.response.AudioLinkResponse
import com.luisfagundes.dictionary.domain.model.AudioLink
import javax.inject.Inject

internal class AudioLinkMapper @Inject constructor() : Mapper<AudioLinkResponse, AudioLink> {
    override fun map(source: AudioLinkResponse) = AudioLink(
        url = source.url,
        type = source.type.orEmpty()
    )
}