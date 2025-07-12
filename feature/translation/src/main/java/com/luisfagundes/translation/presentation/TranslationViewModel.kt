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
class TranslationViewModel @Inject constructor(
    private val getSupportedLanguageListUseCase: GetSupportedLanguageListUseCase,
    private val translateTextUseCase: TranslateTextUseCase,
    @param:Dispatcher(IO) private val dispatcher: CoroutineDispatcher
) : ViewModel<TranslationUiState>(
    initialState = TranslationUiState()
) {
    fun translateText(text: String, targetLang: String) = viewModelScope.launch {
        val params = TranslationParams(
            text = listOf(text),
            targetLanguage = targetLang
        )
        translateTextUseCase.invoke(params)
            .flowOn(dispatcher)
            .onStart { showLoading() }
            .catch { throwable -> showError(throwable) }
            .collect { translationList -> showResult(translationList) }
    }

    private fun showLoading() {
        updateState { state -> state.setLoading(true) }
    }

    private fun showError(throwable: Throwable) {
        val message = throwable.localizedMessage ?: throwable.toString()
        updateState { state -> state.setError(message) }
    }

    private fun showResult(translationList: List<Translation>) {
        updateState { state -> state.setResult(translationList.toString()) }
    }
}