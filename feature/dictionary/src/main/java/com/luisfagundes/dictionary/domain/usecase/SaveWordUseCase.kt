package com.luisfagundes.dictionary.domain.usecase

import com.luisfagundes.dictionary.domain.model.SaveWordParams
import com.luisfagundes.dictionary.domain.model.SupportedLanguage
import com.luisfagundes.dictionary.domain.model.Word
import com.luisfagundes.dictionary.domain.repository.WordRepository
import javax.inject.Inject

internal class SaveWordUseCase @Inject constructor(
    private val repository: WordRepository
) {
    suspend operator fun invoke(
        query: String,
        languagePair: Pair<SupportedLanguage, SupportedLanguage>,
        word: Word
    ) {
        val primaryTranslation = word.translations.firstOrNull()
        if (primaryTranslation != null) {
            val params = SaveWordParams(
                query = query,
                sourceLanguage = languagePair.first,
                targetLanguage = languagePair.second,
                translatedText = primaryTranslation.text,
                partOfSpeech = word.partOfSpeech,
                timestamp = System.currentTimeMillis()
            )
            repository.saveWordToHistory(params)
        }
    }
}