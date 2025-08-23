package com.luisfagundes.translation.domain.usecase

import com.luisfagundes.translation.domain.model.WordHistory
import com.luisfagundes.translation.domain.repository.TranslationRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

internal class GetAllWordsFromHistory @Inject constructor(
    private val repository: TranslationRepository
) {
    operator fun invoke(): Flow<List<WordHistory>> {
        return repository.getSavedTranslations()
    }
}