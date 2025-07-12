package com.luisfagundes.translation.presentation

import androidx.lifecycle.viewModelScope
import com.luisfagundes.common.dispatcher.AppDispatcher.IO
import com.luisfagundes.common.dispatcher.Dispatcher
import com.luisfagundes.common.presentation.ViewModel
import com.luisfagundes.common.provider.ResourceProvider
import com.luisfagundes.translation.R
import com.luisfagundes.translation.domain.model.Language
import com.luisfagundes.translation.domain.model.Translation
import com.luisfagundes.translation.domain.model.TranslationParams
import com.luisfagundes.translation.domain.usecase.GetSupportedLanguageListUseCase
import com.luisfagundes.translation.domain.usecase.TranslateTextUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class TranslationViewModel @Inject constructor(
    private val getSupportedLanguageListUseCase: GetSupportedLanguageListUseCase,
    private val translateTextUseCase: TranslateTextUseCase,
    private val resourceProvider: ResourceProvider,
    @param:Dispatcher(IO) private val dispatcher: CoroutineDispatcher
) : ViewModel<TranslationUiState>(
    initialState = TranslationUiState()
) {
    fun translate(text: String) = viewModelScope.launch {
        if (text.count() < 2) {
            setErrorState(resourceProvider.getString(R.string.min_text_error))
            return@launch
        }
        val params = TranslationParams(
            text = listOf(text),
            sourceLanguage = uiState.value.languagePair.first.code,
            targetLanguage = uiState.value.languagePair.second.code
        )
        translateTextUseCase.invoke(params)
            .flowOn(dispatcher)
            .onStart { setLoadingState() }
            .catch { throwable -> setErrorState(throwable.toString()) }
            .collect { translationList -> setSuccessState(translationList) }
    }

    private fun setLoadingState() {
        updateState { state -> state.setLoading(true) }
    }

    private fun setErrorState(message: String) {
        updateState { state -> state.setError(message) }
    }

    private fun setSuccessState(translationList: List<Translation>) {
        val translatedText = translationList.firstOrNull()?.translatedText ?: ""
        updateState { state -> state.setResult(translatedText) }
    }
}