package com.luisfagundes.dictionary.domain.usecase

import com.luisfagundes.dictionary.domain.model.TranslationParams
import com.luisfagundes.dictionary.domain.repository.TranslationRepository
import javax.inject.Inject

internal class GetTranslationsUseCase @Inject constructor(
    private val repository: TranslationRepository
) {
    operator fun invoke(params: TranslationParams) = repository.getTranslations(params)
}