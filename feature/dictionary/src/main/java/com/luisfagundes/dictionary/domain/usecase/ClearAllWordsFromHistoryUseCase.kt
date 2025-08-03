package com.luisfagundes.dictionary.domain.usecase

import com.luisfagundes.dictionary.domain.repository.WordRepository
import javax.inject.Inject

internal class ClearAllWordsFromHistoryUseCase @Inject constructor(
    private val repository: WordRepository
) {
    suspend operator fun invoke() {
        repository.clearAllHistory()
    }
}