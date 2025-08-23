package com.luisfagundes.translation.domain.usecase

import com.luisfagundes.translation.domain.repository.TranslationRepository
import javax.inject.Inject

internal class ClearAllWordsFromHistoryUseCase @Inject constructor(
    private val repository: TranslationRepository
) {
    suspend operator fun invoke() {
        repository.clearAllSavedWords()
    }
}