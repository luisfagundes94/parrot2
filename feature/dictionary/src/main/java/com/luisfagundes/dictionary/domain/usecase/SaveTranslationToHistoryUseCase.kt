package com.luisfagundes.dictionary.domain.usecase

import com.luisfagundes.dictionary.domain.model.SupportedLanguage
import com.luisfagundes.dictionary.domain.model.Word
import com.luisfagundes.dictionary.domain.repository.TranslationRepository
import javax.inject.Inject

internal class SaveTranslationToHistoryUseCase @Inject constructor(
    private val repository: TranslationRepository
) {
    suspend operator fun invoke(
        query: String,
        sourceLanguage: SupportedLanguage,
        targetLanguage: SupportedLanguage,
        word: Word
    ) {
        val primaryTranslation = word.translations.firstOrNull()
        if (primaryTranslation != null) {
            repository.saveTranslationToHistory(
                query = query,
                sourceLanguage = sourceLanguage,
                targetLanguage = targetLanguage,
                translatedText = primaryTranslation.text,
                partOfSpeech = word.partOfSpeech,
                timestamp = System.currentTimeMillis()
            )
        }
    }
}