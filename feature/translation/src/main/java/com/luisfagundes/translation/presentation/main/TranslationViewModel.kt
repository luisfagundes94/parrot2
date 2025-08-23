package com.luisfagundes.translation.presentation.main

import androidx.lifecycle.viewModelScope
import com.luisfagundes.common.dispatcher.AppDispatcher.IO
import com.luisfagundes.common.dispatcher.Dispatcher
import com.luisfagundes.common.presentation.ViewModel
import com.luisfagundes.common.provider.ResourceProvider
import com.luisfagundes.translation.R
import com.luisfagundes.translation.domain.model.TranslationParams
import com.luisfagundes.translation.domain.model.Word
import com.luisfagundes.translation.domain.usecase.GetLanguagePairUseCase
import com.luisfagundes.translation.domain.usecase.TranslateWordUseCase
import com.luisfagundes.translation.domain.usecase.SaveWordUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class TranslationViewModel @Inject constructor(
    private val translateWordUseCase: TranslateWordUseCase,
    private val saveWordUseCase: SaveWordUseCase,
    private val getLanguagePairUseCase: GetLanguagePairUseCase,
    private val resourceProvider: ResourceProvider,
    @param:Dispatcher(IO) private val dispatcher: CoroutineDispatcher
) : ViewModel<TranslationUiState>(
    initialState = TranslationUiState()
) {
    init {
        setLanguagePair()
    }

    fun translate(text: String) {
        if (text.isBlank()) {
            setErrorState(resourceProvider.getString(R.string.blank_input_text_error))
            return
        }

        viewModelScope.launch {
            val params = createTranslationParams(text)
            translateWordUseCase.invoke(params)
                .flowOn(dispatcher)
                .onStart { setLoadingState() }
                .catch { throwable -> setErrorState(throwable.message.toString()) }
                .collect { words -> setTranslationResult(words) }
        }
    }

    fun updateInputText(text: String) {
        updateState { state -> state.copy(inputText = text) }
    }

    fun swapLanguagePair() {
        updateState { state -> state.copy(languagePair = state.languagePair.swap()) }
    }

    fun saveWord(word: Word, isWordSaved: Boolean) {
        if (isWordSaved) return

        viewModelScope.launch(dispatcher) {
            val currentState = uiState.value

            try {
                saveWordUseCase(
                    query = currentState.inputText,
                    languagePair = currentState.languagePair,
                    word = word
                )
                updateState { state -> state.setIsWordSaved(true) }
            } catch (e: Exception) {
                // TODO(implement toast feedback)
            }
        }
    }

    private fun setLanguagePair() = viewModelScope.launch {
        getLanguagePairUseCase.invoke()
            .collect { languagePair ->
                updateState { state -> state.setLanguagePair(languagePair) }
            }
    }

    private fun createTranslationParams(text: String): TranslationParams {
        val (sourceLanguage, targetLanguage) = uiState.value.languagePair
        return TranslationParams(
            query = text,
            sourceLanguage = sourceLanguage.code,
            targetLanguage = targetLanguage.code
        )
    }

    private fun setTranslationResult(words: List<Word>) {
        if (words.isEmpty()) {
            setErrorState(resourceProvider.getString(R.string.translation_not_found))
        } else {
            setSuccessState(words)
        }
    }

    private fun setLoadingState() {
        updateState { state -> state.setLoading(true) }
    }

    private fun setErrorState(message: String) {
        updateState { state -> state.setError(message) }
    }

    private fun setSuccessState(words: List<Word>) {
        updateState { state -> state.setResult(words) }
    }
}