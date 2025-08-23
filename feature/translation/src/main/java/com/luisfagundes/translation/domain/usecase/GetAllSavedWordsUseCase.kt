package com.luisfagundes.translation.domain.usecase

import com.luisfagundes.translation.domain.model.SavedWord
import com.luisfagundes.translation.domain.repository.TranslationRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

internal class GetAllSavedWordsUseCase @Inject constructor(
    private val repository: TranslationRepository
) {
    operator fun invoke(): Flow<List<SavedWord>> {
        return repository.getSavedWords()
    }
}