package com.luisfagundes.dictionary.presentation.languageselection

import com.luisfagundes.common.presentation.UiState
import com.luisfagundes.dictionary.domain.model.SupportedLanguage

internal data class LanguageSelectionUiState(
    val currentSelectedLanguage: SupportedLanguage? = null,
    val isSourceLanguage: Boolean = true
) : UiState {
    fun setCurrentSelectedLanguage(language: SupportedLanguage?) = this.copy(
        currentSelectedLanguage = language
    )

    fun setIsSourceLanguage(source: Boolean) = this.copy(
        isSourceLanguage = source
    )
}