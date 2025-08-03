package com.luisfagundes.dictionary.domain.usecase

import com.luisfagundes.dictionary.domain.model.LanguagePair
import com.luisfagundes.dictionary.domain.repository.LanguagePairRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


internal class GetLanguagePairUseCase @Inject constructor(
    private val repository: LanguagePairRepository
) {
    fun invoke(): Flow<LanguagePair> {
        return repository.getLanguagePair()
    }
}