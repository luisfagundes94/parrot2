package com.luisfagundes.dictionary.presentation

import com.luisfagundes.common.presentation.UiState
import com.luisfagundes.dictionary.domain.model.SupportedLanguage
import com.luisfagundes.dictionary.domain.model.Word

internal data class DictionaryUiState(
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
        get() = words.all { word ->
            word.translations.all { translation ->
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