package com.luisfagundes.translation.domain.usecase

import com.luisfagundes.translation.domain.repository.WordRepository
import javax.inject.Inject

internal class ClearAllWordsFromHistoryUseCase @Inject constructor(
    private val repository: WordRepository
) {
    suspend operator fun invoke() {
        repository.clearAllHistory()
    }
}