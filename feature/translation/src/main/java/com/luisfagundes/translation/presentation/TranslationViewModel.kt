package com.luisfagundes.translation.presentation

import androidx.lifecycle.viewModelScope
import com.luisfagundes.common.dispatcher.AppDispatcher.IO
import com.luisfagundes.common.dispatcher.Dispatcher
import com.luisfagundes.common.presentation.ViewModel
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
    @param:Dispatcher(IO) private val dispatcher: CoroutineDispatcher
) : ViewModel<TranslationUiState>(
    initialState = TranslationUiState()
) {
    fun translate(text: String, targetLang: String) = viewModelScope.launch {
        val params = TranslationParams(
            text = listOf(text),
            targetLanguage = targetLang
        )
        translateTextUseCase.invoke(params)
            .flowOn(dispatcher)
            .onStart { setLoadingState() }
            .catch { throwable -> setErrorState(throwable) }
            .collect { translationList -> setSuccessState(translationList) }
    }

    private fun setLoadingState() {
        updateState { state -> state.setLoading(true) }
    }

    private fun setErrorState(throwable: Throwable) {
        val message = throwable.localizedMessage ?: throwable.toString()
        updateState { state -> state.setError(message) }
    }

    private fun setSuccessState(translationList: List<Translation>) {
        updateState { state -> state.setResult(translationList.toString()) }
    }
}