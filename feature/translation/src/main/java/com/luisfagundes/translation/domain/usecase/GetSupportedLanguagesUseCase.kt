package com.luisfagundes.translation.domain.usecase

import com.luisfagundes.translation.domain.model.Language
import com.luisfagundes.translation.domain.repository.TranslationRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSupportedLanguagesUseCase @Inject constructor(
    private val repository: TranslationRepository
) {
    fun getSourceLanguages(): Flow<List<Language>> {
        return repository.getSourceLanguages()
    }
    
    fun getTargetLanguages(): Flow<List<Language>> {
        return repository.getTargetLanguages()
    }
}