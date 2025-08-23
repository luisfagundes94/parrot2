package com.luisfagundes.translation.presentation.main

import com.luisfagundes.common.presentation.UiState
import com.luisfagundes.translation.domain.model.LanguagePair
import com.luisfagundes.translation.domain.model.Word

internal data class TranslationUiState(
    val languagePair: LanguagePair = LanguagePair(),
    val inputText: String = "",
    val words: List<Word> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String = "",
    val isWordSaved: Boolean = false,
) : UiState {

    val hasExamples: Boolean
        get() = words.any { word ->
            word.translations.any { translation ->
                translation.examples.isNotEmpty()
            }
        }

    fun setLanguagePair(languagePair: LanguagePair) = this.copy(
        languagePair = languagePair
    )

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

    fun setIsWordSaved(isSaved: Boolean) = this.copy(
        isWordSaved = isSaved
    )
}