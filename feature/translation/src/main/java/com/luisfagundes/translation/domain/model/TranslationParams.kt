package com.luisfagundes.translation.domain.model

data class TranslationParams(
    val text: List<String>,
    val targetLanguage: String,
    val sourceLanguage: String? = null,
    val formality: String? = null,
    val preserveFormatting: Boolean = false
)
