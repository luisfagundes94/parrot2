package com.luisfagundes.dictionary.domain.usecase

import com.luisfagundes.dictionary.domain.repository.TranslationRepository
import javax.inject.Inject

internal class ClearAllHistoryUseCase @Inject constructor(
    private val repository: TranslationRepository
) {
    suspend operator fun invoke() {
        repository.clearAllHistory()
    }
}