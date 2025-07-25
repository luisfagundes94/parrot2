package com.luisfagundes.translation.domain.usecase

import com.luisfagundes.translation.domain.model.TranslationParams
import com.luisfagundes.translation.domain.repository.TranslationRepository
import javax.inject.Inject

internal class GetTranslationsUseCase @Inject constructor(
    private val repository: TranslationRepository
) {
    operator fun invoke(params: TranslationParams) = repository.getTranslations(params)
}