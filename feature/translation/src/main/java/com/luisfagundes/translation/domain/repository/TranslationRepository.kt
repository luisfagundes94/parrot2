package com.luisfagundes.translation.domain.repository

import com.luisfagundes.translation.domain.model.Language
import com.luisfagundes.translation.domain.model.Translation
import com.luisfagundes.translation.domain.model.TranslationParams
import kotlinx.coroutines.flow.Flow

interface TranslationRepository {
    fun translateText(params: TranslationParams): Flow<List<Translation>>
    fun getSourceLanguages(): Flow<List<Language>>
    fun getTargetLanguages(): Flow<List<Language>>
}