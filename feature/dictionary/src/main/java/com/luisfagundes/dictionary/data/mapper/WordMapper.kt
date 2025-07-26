package com.luisfagundes.dictionary.data.mapper

import com.luisfagundes.dictionary.data.model.response.WordResponse
import com.luisfagundes.dictionary.domain.model.Word
import javax.inject.Inject

internal class WordMapper @Inject constructor(
    private val audioLinkMapper: AudioLinkMapper,
    private val translationMapper: TranslationMapper
) {
    fun mapToDomain(source: WordResponse) = Word(
        featured = source.featured,
        translatedText = source.text,
        partOfSpeech = source.pos,
        forms = source.forms,
        grammarInfo = source.grammarInfo.orEmpty(),
        audioLinks = source.audioLinks.map { audioLinkMapper.mapToDomain(it) },
        translations = source.translations.map { translationMapper.mapToDomain(it) }
    )
}