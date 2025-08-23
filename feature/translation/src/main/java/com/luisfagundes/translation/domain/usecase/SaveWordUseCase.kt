package com.luisfagundes.translation.domain.usecase

import com.luisfagundes.translation.domain.model.LanguagePair
import com.luisfagundes.translation.domain.model.SaveWordParams
import com.luisfagundes.translation.domain.model.Word
import com.luisfagundes.translation.domain.repository.WordRepository
import javax.inject.Inject

internal class SaveWordUseCase @Inject constructor(
    private val repository: WordRepository
) {
    suspend operator fun invoke(
        query: String,
        languagePair: LanguagePair,
        word: Word
    ) {
        val primaryTranslation = word.translations.firstOrNull()
        if (primaryTranslation != null) {
            val params = SaveWordParams(
                query = query,
                sourceLanguage = languagePair.sourceLanguage,
                targetLanguage = languagePair.targetLanguage,
                translatedText = primaryTranslation.text,
                partOfSpeech = word.partOfSpeech,
                timestamp = System.currentTimeMillis()
            )
            repository.saveWordToHistory(params)
        }
    }
}