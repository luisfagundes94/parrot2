package com.luisfagundes.translation.domain.usecase

import com.luisfagundes.translation.domain.model.Translation
import com.luisfagundes.translation.domain.model.TranslationParams
import com.luisfagundes.translation.domain.repository.TranslationRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TranslateTextUseCase @Inject constructor(
    private val repository: TranslationRepository
) {
    operator fun invoke(
        params: TranslationParams
    ): Flow<List<Translation>> {
        return repository.translateText(
            params = params
        )
    }
}