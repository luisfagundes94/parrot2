package com.luisfagundes.translation.domain.repository

import com.luisfagundes.translation.domain.model.TranslationParams
import com.luisfagundes.translation.domain.model.Word
import kotlinx.coroutines.flow.Flow

internal interface TranslationRepository {
    fun getTranslations(params: TranslationParams): Flow<List<Word>>
}