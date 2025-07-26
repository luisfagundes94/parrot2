package com.luisfagundes.dictionary.data.mapper

import com.luisfagundes.dictionary.data.model.response.WordResponse
import com.luisfagundes.dictionary.domain.model.Word
import javax.inject.Inject

internal class WordMapper @Inject constructor(
    private val audioLinkMapper: AudioLinkMapper,
    private val translationMapper: TranslationMapper
) {
    fun toDomain(source: WordResponse) = Word(
        featured = source.featured,
        translatedText = source.text,
        partOfSpeech = source.pos,
        forms = source.forms,
        grammarInfo = source.grammarInfo.orEmpty(),
        audioLinks = source.audioLinks.map { audioLinkMapper.toDomain(it) },
        translations = source.translations.map { translationMapper.toDomain(it) }
    )
}