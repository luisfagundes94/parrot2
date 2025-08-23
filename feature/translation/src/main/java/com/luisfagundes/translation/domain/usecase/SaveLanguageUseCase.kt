package com.luisfagundes.translation.domain.usecase

import com.luisfagundes.translation.domain.model.SupportedLanguage
import com.luisfagundes.translation.domain.repository.LanguagePairRepository
import javax.inject.Inject


internal class SaveLanguageUseCase @Inject constructor(
    private val repository: LanguagePairRepository
) {
    suspend operator fun invoke(language: SupportedLanguage, isSourceLanguage: Boolean) {
        if (isSourceLanguage) repository.saveSourceLanguage(language)
        else repository.saveTargetLanguage(language)
    }
}