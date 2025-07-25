package com.luisfagundes.dictionary.data.mapper

import com.luisfagundes.common.mapper.Mapper
import com.luisfagundes.dictionary.data.database.entities.TranslationHistoryEntity
import com.luisfagundes.dictionary.domain.model.SupportedLanguage
import com.luisfagundes.dictionary.domain.model.TranslationHistoryItem
import javax.inject.Inject

internal class TranslationHistoryMapper @Inject constructor() : 
    Mapper<TranslationHistoryEntity, TranslationHistoryItem> {
    
    override fun map(input: TranslationHistoryEntity): TranslationHistoryItem {
        return TranslationHistoryItem(
            id = input.id,
            query = input.query,
            sourceLanguage = SupportedLanguage.entries.find { it.code == input.sourceLanguage } 
                ?: SupportedLanguage.English,
            targetLanguage = SupportedLanguage.entries.find { it.code == input.targetLanguage } 
                ?: SupportedLanguage.Portuguese,
            translatedText = input.translatedText,
            partOfSpeech = input.partOfSpeech,
            timestamp = input.timestamp
        )
    }
    
    fun mapToEntity(
        query: String,
        sourceLanguage: SupportedLanguage,
        targetLanguage: SupportedLanguage,
        translatedText: String,
        partOfSpeech: String,
        timestamp: Long
    ): TranslationHistoryEntity {
        return TranslationHistoryEntity(
            query = query,
            sourceLanguage = sourceLanguage.code,
            targetLanguage = targetLanguage.code,
            translatedText = translatedText,
            partOfSpeech = partOfSpeech,
            timestamp = timestamp
        )
    }
}