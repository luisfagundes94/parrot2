package com.luisfagundes.dictionary.domain.model

internal data class Word(
    val featured: Boolean,
    val text: String,
    val partOfSpeech: String,
    val forms: List<String>,
    val grammarInfo: String,
    val audioLinks: List<AudioLink>,
    val translations: List<Translation>
)
