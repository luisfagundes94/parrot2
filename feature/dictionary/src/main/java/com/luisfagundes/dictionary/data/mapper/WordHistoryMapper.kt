package com.luisfagundes.dictionary.data.mapper

import com.luisfagundes.dictionary.data.database.entities.TranslationHistoryEntity
import com.luisfagundes.dictionary.domain.model.SaveWordParams
import com.luisfagundes.dictionary.domain.model.SupportedLanguage
import com.luisfagundes.dictionary.domain.model.WordHistory
import javax.inject.Inject

internal class WordHistoryMapper @Inject constructor() {
    fun toDomain(source: TranslationHistoryEntity): WordHistory {
        return WordHistory(
            id = source.id,
            query = source.query,
            sourceLanguage = SupportedLanguage.entries.find { it.code == source.sourceLanguage }
                ?: SupportedLanguage.English,
            targetLanguage = SupportedLanguage.entries.find { it.code == source.targetLanguage }
                ?: SupportedLanguage.Portuguese,
            translatedText = source.translatedText,
            partOfSpeech = source.partOfSpeech,
            timestamp = source.timestamp
        )
    }

    fun toEntity(
        params: SaveWordParams
    ): TranslationHistoryEntity {
        return TranslationHistoryEntity(
            query = params.query,
            sourceLanguage = params.sourceLanguage.code,
            targetLanguage = params.targetLanguage.code,
            translatedText = params.translatedText,
            partOfSpeech = params.partOfSpeech,
            timestamp = params.timestamp
        )
    }
}