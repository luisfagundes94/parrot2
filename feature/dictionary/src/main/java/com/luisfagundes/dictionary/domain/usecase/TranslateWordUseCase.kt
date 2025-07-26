package com.luisfagundes.dictionary.domain.usecase

import com.luisfagundes.dictionary.domain.model.TranslationParams
import com.luisfagundes.dictionary.domain.repository.WordRepository
import javax.inject.Inject

internal class TranslateWordUseCase @Inject constructor(
    private val repository: WordRepository
) {
    operator fun invoke(params: TranslationParams) = repository.getTranslations(params)
}