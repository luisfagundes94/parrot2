package com.luisfagundes.dictionary.domain.usecase

import com.luisfagundes.dictionary.domain.repository.WordRepository
import javax.inject.Inject

internal class DeleteWordUseCase @Inject constructor(
    private val repository: WordRepository
) {
    suspend operator fun invoke(id: Long) {
        repository.deleteWordFromHistory(id)
    }
}