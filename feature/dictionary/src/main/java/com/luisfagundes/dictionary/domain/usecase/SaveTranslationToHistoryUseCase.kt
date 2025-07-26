package com.luisfagundes.dictionary.domain.usecase

import com.luisfagundes.dictionary.domain.model.SaveTranslationParams
import com.luisfagundes.dictionary.domain.model.SupportedLanguage
import com.luisfagundes.dictionary.domain.model.Word
import com.luisfagundes.dictionary.domain.repository.TranslationRepository
import javax.inject.Inject

internal class SaveTranslationToHistoryUseCase @Inject constructor(
    private val repository: TranslationRepository
) {
    suspend operator fun invoke(
        query: String,
        languagePair: Pair<SupportedLanguage, SupportedLanguage>,
        word: Word
    ) {
        val primaryTranslation = word.translations.firstOrNull()
        if (primaryTranslation != null) {
            val params = SaveTranslationParams(
                query = query,
                sourceLanguage = languagePair.first,
                targetLanguage = languagePair.second,
                translatedText = primaryTranslation.text,
                partOfSpeech = word.partOfSpeech,
                timestamp = System.currentTimeMillis()
            )
            repository.saveTranslationToHistory(params)
        }
    }
}