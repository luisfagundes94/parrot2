package com.luisfagundes.translation.domain.usecase

import com.luisfagundes.translation.domain.model.SavedWord
import com.luisfagundes.translation.domain.repository.WordRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

internal class GetAllSavedWordsUseCase @Inject constructor(
    private val repository: WordRepository
) {
    operator fun invoke(): Flow<List<SavedWord>> {
        return repository.getSavedWords()
    }
}