package com.luisfagundes.translation.presentation

import com.luisfagundes.common.presentation.UiState
import com.luisfagundes.translation.domain.model.Language
import com.luisfagundes.translation.domain.model.SupportedLanguage.ENGLISH
import com.luisfagundes.translation.domain.model.SupportedLanguage.PORTUGUESE

data class TranslationUiState(
    val languagePair: Pair<Language, Language> = Pair(
        ENGLISH.language,
        PORTUGUESE.language
    ),
    val inputText: String = "",
    val translatedText: String = "",
    val isLoading: Boolean = false,
    val errorMessage: String = "",
) : UiState
