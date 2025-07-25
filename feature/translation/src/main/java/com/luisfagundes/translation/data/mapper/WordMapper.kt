package com.luisfagundes.translation.data.mapper

import com.luisfagundes.common.mapper.Mapper
import com.luisfagundes.translation.data.model.response.WordResponse
import com.luisfagundes.translation.domain.model.Word
import javax.inject.Inject

internal class WordMapper @Inject constructor(
    private val audioLinkMapper: AudioLinkMapper,
    private val translationMapper: TranslationMapper
) : Mapper<WordResponse, Word> {
    override fun map(source: WordResponse) = Word(
        featured = source.featured,
        text = source.text,
        partOfSpeech = source.pos,
        forms = source.forms,
        grammarInfo = source.grammarInfo.orEmpty(),
        audioLinks = source.audioLinks.map { audioLinkMapper.map(it) },
        translations = source.translations.map { translationMapper.map(it) }
    )
}