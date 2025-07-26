package com.luisfagundes.dictionary.domain.usecase

import com.luisfagundes.dictionary.domain.model.WordHistory
import com.luisfagundes.dictionary.domain.repository.WordRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

internal class GetWordHistoryUseCase @Inject constructor(
    private val repository: WordRepository
) {
    operator fun invoke(): Flow<List<WordHistory>> {
        return repository.getHistory()
    }
}