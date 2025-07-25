package com.luisfagundes.translation.presentation

import com.luisfagundes.common.presentation.UiState
import com.luisfagundes.translation.domain.model.SupportedLanguage
import com.luisfagundes.translation.domain.model.Word

internal data class TranslationUiState(
    val languagePair: Pair<SupportedLanguage, SupportedLanguage> = Pair(
        SupportedLanguage.English,
        SupportedLanguage.Portuguese
    ),
    val inputText: String = "",
    val words: List<Word> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String = "",
) : UiState {

    val hasExamples: Boolean
        get() = words.any { word ->
            word.translations.any { translation ->
                translation.examples.isNotEmpty()
            }
        }

    fun setResult(words: List<Word>) = this.copy(
        words = words,
        isLoading = false,
        errorMessage = ""
    )

    fun setLoading(loading: Boolean) = this.copy(
        isLoading = loading,
        errorMessage = "",
        words = emptyList()
    )

    fun setError(message: String) = this.copy(
        isLoading = false,
        errorMessage = message,
        words = emptyList()
    )
}