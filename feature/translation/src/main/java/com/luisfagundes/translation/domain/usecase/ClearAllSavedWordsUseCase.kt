package com.luisfagundes.translation.domain.usecase

import com.luisfagundes.translation.domain.repository.WordRepository
import javax.inject.Inject

internal class ClearAllSavedWordsUseCase @Inject constructor(
    private val repository: WordRepository
) {
    suspend operator fun invoke() {
        repository.clearAllSavedWords()
    }
}