package com.luisfagundes.dictionary.domain.usecase

import com.luisfagundes.dictionary.domain.repository.WordRepository
import javax.inject.Inject

internal class DeleteWordFromHistoryUseCase @Inject constructor(
    private val repository: WordRepository
) {
    suspend operator fun invoke(id: Long) {
        repository.deleteWordFromHistory(id)
    }
}