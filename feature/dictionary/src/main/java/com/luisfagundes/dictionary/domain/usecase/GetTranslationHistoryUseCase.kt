package com.luisfagundes.dictionary.domain.usecase

import com.luisfagundes.dictionary.domain.model.TranslationHistoryItem
import com.luisfagundes.dictionary.domain.repository.TranslationRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

internal class GetTranslationHistoryUseCase @Inject constructor(
    private val repository: TranslationRepository
) {
    operator fun invoke(): Flow<List<TranslationHistoryItem>> {
        return repository.getTranslationHistory()
    }
}