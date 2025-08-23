package com.luisfagundes.translation.data.mapper

import com.luisfagundes.translation.data.database.entities.SavedTranslationEntity
import com.luisfagundes.translation.domain.model.SaveWordParams
import com.luisfagundes.translation.domain.model.SupportedLanguage
import com.luisfagundes.translation.domain.model.WordHistory
import javax.inject.Inject

internal class WordHistoryMapper @Inject constructor() {
    fun toDomain(source: SavedTranslationEntity): WordHistory {
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
    ): SavedTranslationEntity {
        return SavedTranslationEntity(
            query = params.query,
            sourceLanguage = params.sourceLanguage.code,
            targetLanguage = params.targetLanguage.code,
            translatedText = params.translatedText,
            partOfSpeech = params.partOfSpeech,
            timestamp = params.timestamp
        )
    }
}