package com.luisfagundes.dictionary.presentation

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
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class DictionaryViewModel @Inject constructor(
    private val getTranslationsUseCase: GetTranslationsUseCase,
    private val resourceProvider: ResourceProvider,
    @param:Dispatcher(IO) private val dispatcher: CoroutineDispatcher
) : ViewModel<DictionaryUiState>(
    initialState = DictionaryUiState()
) {
    fun translate(text: String) {
        if (text.isBlank()) {
            setErrorState(resourceProvider.getString(R.string.blank_input_text_error))
            return
        }
        viewModelScope.launch {
            val params = createTranslationParams(text)
            executeTranslation(params)
        }
    }

    fun swapLanguagePair() {
        updateState { state -> state.copy(languagePair = state.languagePair.swap()) }
    }

    private fun createTranslationParams(text: String): TranslationParams {
        return with(uiState.value.languagePair) {
            TranslationParams(
                query = text,
                sourceLanguage = first.code,
                targetLanguage = second.code
            )
        }
    }

    private suspend fun executeTranslation(params: TranslationParams) {
        getTranslationsUseCase.invoke(params)
            .flowOn(dispatcher)
            .onStart { setLoadingState() }
            .catch { throwable -> setErrorState(throwable.message.toString()) }
            .collect { words ->
                if (words.isEmpty()) setErrorState(
                    message = resourceProvider.getString(R.string.translation_not_found)
                ) else {
                    setSuccessState(words)
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