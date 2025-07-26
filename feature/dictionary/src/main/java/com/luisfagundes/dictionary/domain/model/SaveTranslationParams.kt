package com.luisfagundes.dictionary.domain.model

internal data class SaveTranslationParams(
    val query: String,
    val sourceLanguage: SupportedLanguage,
    val targetLanguage: SupportedLanguage,
    val translatedText: String,
    val partOfSpeech: String,
    val timestamp: Long
)