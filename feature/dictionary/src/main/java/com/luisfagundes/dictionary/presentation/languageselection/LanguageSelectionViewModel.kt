package com.luisfagundes.dictionary.presentation.languageselection

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.luisfagundes.common.dispatcher.AppDispatcher.IO
import com.luisfagundes.common.dispatcher.Dispatcher
import com.luisfagundes.common.presentation.ViewModel
import com.luisfagundes.dictionary.domain.model.SupportedLanguage
import com.luisfagundes.dictionary.domain.usecase.SaveLanguageUseCase
import com.luisfagundes.dictionary.presentation.languageselection.navigation.LanguageSelectionRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class LanguageSelectionViewModel @Inject constructor(
    private val saveLanguageUseCase: SaveLanguageUseCase,
    @param:Dispatcher(IO) private val dispatcher: CoroutineDispatcher,
    savedStateHandle: SavedStateHandle
) : ViewModel<LanguageSelectionUiState>(
    initialState = LanguageSelectionUiState()
) {
    private val args = savedStateHandle.toRoute<LanguageSelectionRoute>()

    init {
        setIsSourceLanguage()
        setCurrentSelectedLanguage()
    }

    fun saveLanguage(language: SupportedLanguage) = viewModelScope.launch(dispatcher) {
        saveLanguageUseCase.invoke(
            language = language,
            isSourceLanguage = args.isSourceLanguage
        )
    }

    private fun setIsSourceLanguage() {
        updateState { state -> state.setIsSourceLanguage(args.isSourceLanguage) }
    }

    private fun setCurrentSelectedLanguage() {
        val language = SupportedLanguage.entries.find { it.code == args.currentLanguageCode }
        updateState { state -> state.setCurrentSelectedLanguage(language) }
    }
}