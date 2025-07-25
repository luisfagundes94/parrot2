package com.luisfagundes.dictionary.presentation.translation

import androidx.lifecycle.viewModelScope
import com.luisfagundes.common.dispatcher.AppDispatcher.IO
import com.luisfagundes.common.dispatcher.Dispatcher
import com.luisfagundes.common.extensions.swap
import com.luisfagundes.common.presentation.ViewModel
import com.luisfagundes.common.provider.ResourceProvider
import com.luisfagundes.dictionary.R
import com.luisfagundes.dictionary.domain.model.TranslationParams
import com.luisfagundes.dictionary.domain.model.Word
import com.luisfagundes.dictionary.domain.usecase.GetTranslationsUseCase
import com.luisfagundes.dictionary.domain.usecase.SaveTranslationToHistoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class TranslationViewModel @Inject constructor(
    private val getTranslationsUseCase: GetTranslationsUseCase,
    private val saveTranslationToHistoryUseCase: SaveTranslationToHistoryUseCase,
    private val resourceProvider: ResourceProvider,
    @param:Dispatcher(IO) private val dispatcher: CoroutineDispatcher
) : ViewModel<TranslationUiState>(
    initialState = TranslationUiState()
) {
    fun translate(text: String) {
        if (text.isBlank()) {
            setErrorState(resourceProvider.getString(R.string.blank_input_text_error))
            return
        }
        
        // Update input text in state
        updateState { state -> state.copy(inputText = text) }
        
        viewModelScope.launch {
            val params = createTranslationParams(text)
            getTranslationsUseCase.invoke(params)
                .flowOn(dispatcher)
                .onStart { setLoadingState() }
                .catch { throwable -> setErrorState(throwable.message.toString()) }
                .collect { words -> handleTranslationResult(words) }
        }
    }

    fun swapLanguagePair() {
        updateState { state -> state.copy(languagePair = state.languagePair.swap()) }
    }

    private fun createTranslationParams(text: String): TranslationParams {
        val (sourceLanguage, targetLanguage) = uiState.value.languagePair
        return TranslationParams(
            query = text,
            sourceLanguage = sourceLanguage.code,
            targetLanguage = targetLanguage.code
        )
    }

    private fun handleTranslationResult(words: List<Word>) {
        if (words.isEmpty()) {
            setErrorState(resourceProvider.getString(R.string.translation_not_found))
        } else {
            setSuccessState(words)
            saveTranslationToHistory(words.first())
        }
    }
    
    private fun saveTranslationToHistory(word: Word) {
        viewModelScope.launch(dispatcher) {
            try {
                val currentState = uiState.value
                val (sourceLanguage, targetLanguage) = currentState.languagePair
                saveTranslationToHistoryUseCase(
                    query = currentState.inputText,
                    sourceLanguage = sourceLanguage,
                    targetLanguage = targetLanguage,
                    word = word
                )
            } catch (e: Exception) {
                // Silently fail for history saving - don't disturb user experience
            }
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