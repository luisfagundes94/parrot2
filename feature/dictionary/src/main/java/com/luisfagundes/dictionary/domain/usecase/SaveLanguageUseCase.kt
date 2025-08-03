package com.luisfagundes.dictionary.domain.usecase

import com.luisfagundes.dictionary.domain.model.SupportedLanguage
import com.luisfagundes.dictionary.domain.repository.LanguagePairRepository
import javax.inject.Inject


internal class SaveLanguageUseCase @Inject constructor(
    private val repository: LanguagePairRepository
) {
    suspend operator fun invoke(language: SupportedLanguage, isSourceLanguage: Boolean) {
        if (isSourceLanguage) repository.saveSourceLanguage(language)
        else repository.saveTargetLanguage(language)
    }
}