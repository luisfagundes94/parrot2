package com.luisfagundes.dictionary.data.repository

import com.luisfagundes.dictionary.data.datastore.LanguagePairDataStore
import com.luisfagundes.dictionary.domain.model.LanguagePair
import com.luisfagundes.dictionary.domain.model.SupportedLanguage
import com.luisfagundes.dictionary.domain.repository.LanguagePairRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

internal class LanguagePairRepositoryImpl @Inject constructor(
    private val dataStore: LanguagePairDataStore
) : LanguagePairRepository {
    
    override suspend fun saveSourceLanguage(language: SupportedLanguage) {
        dataStore.saveSourceLanguage(language)
    }

    override suspend fun saveTargetLanguage(language: SupportedLanguage) {
        dataStore.saveTargetLanguage(language)
    }

    override fun getLanguagePair(): Flow<LanguagePair> {
        return dataStore.getLanguagePair()
    }
}