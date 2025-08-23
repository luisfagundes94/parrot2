package com.luisfagundes.translation.domain.usecase

import com.luisfagundes.translation.domain.model.TranslationParams
import com.luisfagundes.translation.domain.repository.WordRepository
import javax.inject.Inject

internal class TranslateWordUseCase @Inject constructor(
    private val repository: WordRepository
) {
    operator fun invoke(params: TranslationParams) = repository.translate(params)
}