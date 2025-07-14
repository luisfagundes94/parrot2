package com.luisfagundes.dictionary.presentation.components

import androidx.compose.runtime.Composable
import com.luisfagundes.dictionary.domain.model.Word

@Composable
internal fun DictionaryResult(
    words: List<Word>
) {
    if (words.isEmpty()) return

    TranslationsResult(
        words = words
    )
    ExamplesResult(
        words = words
    )
}