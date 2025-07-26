package com.luisfagundes.dictionary.data.mapper

import com.luisfagundes.dictionary.data.database.entities.TranslationHistoryEntity
import com.luisfagundes.dictionary.domain.model.SaveTranslationParams
import com.luisfagundes.dictionary.domain.model.SupportedLanguage
import com.luisfagundes.dictionary.domain.model.TranslationHistoryItem
import javax.inject.Inject

internal class TranslationHistoryMapper @Inject constructor() {
    fun mapToDomain(source: TranslationHistoryEntity): TranslationHistoryItem {
        return TranslationHistoryItem(
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

    fun mapToEntity(
        params: SaveTranslationParams
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