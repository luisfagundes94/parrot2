package com.luisfagundes.translation.domain.usecase

import com.luisfagundes.translation.domain.repository.TranslationRepository
import javax.inject.Inject

internal class DeleteSavedWordUseCase @Inject constructor(
    private val repository: TranslationRepository
) {
    suspend operator fun invoke(id: Long) {
        repository.deleteSavedWord(id)
    }
}