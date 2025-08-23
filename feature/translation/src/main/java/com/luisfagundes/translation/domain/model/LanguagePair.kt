package com.luisfagundes.translation.domain.model

internal data class LanguagePair(
    val sourceLanguage: SupportedLanguage = SupportedLanguage.English,
    val targetLanguage: SupportedLanguage = SupportedLanguage.Portuguese
) {
    fun swap() = this.copy(
        sourceLanguage = targetLanguage,
        targetLanguage = sourceLanguage
    )
}