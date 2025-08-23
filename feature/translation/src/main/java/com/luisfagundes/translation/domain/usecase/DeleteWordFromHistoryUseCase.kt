package com.luisfagundes.translation.domain.usecase

import com.luisfagundes.translation.domain.repository.WordRepository
import javax.inject.Inject

internal class DeleteWordFromHistoryUseCase @Inject constructor(
    private val repository: WordRepository
) {
    suspend operator fun invoke(id: Long) {
        repository.deleteWordFromHistory(id)
    }
}