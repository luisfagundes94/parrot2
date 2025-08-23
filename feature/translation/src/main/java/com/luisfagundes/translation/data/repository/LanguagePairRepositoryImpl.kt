package com.luisfagundes.translation.data.repository

import com.luisfagundes.translation.data.datastore.LanguagePairDataStore
import com.luisfagundes.translation.domain.model.LanguagePair
import com.luisfagundes.translation.domain.model.SupportedLanguage
import com.luisfagundes.translation.domain.repository.LanguagePairRepository
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