package com.luisfagundes.dictionary.domain.repository

import com.luisfagundes.dictionary.domain.model.TranslationParams
import com.luisfagundes.dictionary.domain.model.Word
import kotlinx.coroutines.flow.Flow

internal interface DictionaryRepository {
    fun getTranslations(params: TranslationParams): Flow<List<Word>>
}