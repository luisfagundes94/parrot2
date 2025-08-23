package com.luisfagundes.translation.domain.model

internal data class Word(
    val featured: Boolean,
    val translatedText: String,
    val partOfSpeech: String,
    val forms: List<String>,
    val grammarInfo: String,
    val audioLinks: List<AudioLink>,
    val translations: List<Translation>
)
