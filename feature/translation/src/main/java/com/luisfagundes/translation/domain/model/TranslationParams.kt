package com.luisfagundes.translation.domain.model

internal data class TranslationParams(
    val query: String,
    val sourceLanguage: String,
    val targetLanguage: String
)
