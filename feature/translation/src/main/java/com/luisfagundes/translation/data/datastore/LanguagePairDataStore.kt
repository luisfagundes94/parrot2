package com.luisfagundes.translation.data.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.luisfagundes.translation.domain.model.LanguagePair
import com.luisfagundes.translation.domain.model.SupportedLanguage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
    name = "language_pair_preferences"
)

@Singleton
internal class LanguagePairDataStore @Inject constructor(
    private val context: Context
) {
    private object PreferencesKeys {
        val SOURCE_LANGUAGE = stringPreferencesKey("source_language")
        val TARGET_LANGUAGE = stringPreferencesKey("target_language")
    }

    suspend fun saveSourceLanguage(language: SupportedLanguage) {
        context.dataStore.edit { preferences ->
            preferences[PreferencesKeys.SOURCE_LANGUAGE] = language.code
        }
    }

    suspend fun saveTargetLanguage(language: SupportedLanguage) {
        context.dataStore.edit { preferences ->
            preferences[PreferencesKeys.TARGET_LANGUAGE] = language.code
        }
    }

    fun getLanguagePair(): Flow<LanguagePair> {
        return context.dataStore.data.map { preferences ->
            val sourceCode = preferences[PreferencesKeys.SOURCE_LANGUAGE]
            val targetCode = preferences[PreferencesKeys.TARGET_LANGUAGE]

            val defaultSourceLanguage = SupportedLanguage.English
            val defaultTargetLanguage = SupportedLanguage.Portuguese

            val sourceLanguage = SupportedLanguage.entries.find { it.code == sourceCode }
            val targetLanguage = SupportedLanguage.entries.find { it.code == targetCode }

            LanguagePair(
                sourceLanguage = sourceLanguage ?: defaultSourceLanguage,
                targetLanguage = targetLanguage ?: defaultTargetLanguage
            )
        }
    }
}