package com.luisfagundes.translation.domain.usecase

import com.luisfagundes.translation.domain.model.WordHistory
import com.luisfagundes.translation.domain.repository.WordRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

internal class GetAllWordsFromHistory @Inject constructor(
    private val repository: WordRepository
) {
    operator fun invoke(): Flow<List<WordHistory>> {
        return repository.getHistory()
    }
}