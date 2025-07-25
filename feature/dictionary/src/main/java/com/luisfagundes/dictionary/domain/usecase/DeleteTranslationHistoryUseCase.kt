package com.luisfagundes.dictionary.domain.usecase

import com.luisfagundes.dictionary.domain.repository.TranslationRepository
import javax.inject.Inject

internal class DeleteTranslationHistoryUseCase @Inject constructor(
    private val repository: TranslationRepository
) {
    suspend operator fun invoke(id: Long) {
        repository.deleteTranslationHistory(id)
    }
}