package com.luisfagundes.translation.presentation

import androidx.lifecycle.viewModelScope
import com.luisfagundes.common.dispatcher.AppDispatcher.IO
import com.luisfagundes.common.dispatcher.Dispatcher
import com.luisfagundes.common.presentation.ViewModel
import com.luisfagundes.translation.domain.model.Language
import com.luisfagundes.translation.domain.model.Translation
import com.luisfagundes.translation.domain.model.TranslationParams
import com.luisfagundes.translation.domain.usecase.GetSupportedLanguageListUseCase
import com.luisfagundes.translation.domain.usecase.TranslateTextUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
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
    private var translationJob: Job? = null
    private var lastTranslatedText: String = ""
    private val translationCache = mutableMapOf<String, String>()
    
    companion object {
        private const val DEBOUNCE_DELAY_MS = 500L
        private const val MIN_TEXT_LENGTH = 3
    }

    fun onTextChanged(text: String, targetLang: Language) {
        translationJob?.cancel()

        if (text.isBlank() || text.length < MIN_TEXT_LENGTH) return
        if (text == lastTranslatedText) return

        val cacheKey = "${text}_${targetLang.code}"

        translationCache[cacheKey]?.let { cachedResult ->
            updateState { state -> state.setResult(cachedResult) }
            lastTranslatedText = text
            return
        }

        translationJob = viewModelScope.launch {
            delay(DEBOUNCE_DELAY_MS)
            performTranslation(text, targetLang, cacheKey)
        }
    }
    
    private suspend fun performTranslation(text: String, targetLang: Language, cacheKey: String) {
        val params = TranslationParams(
            text = listOf(text),
            targetLanguage = targetLang.code
        )
        translateTextUseCase.invoke(params)
            .flowOn(dispatcher)
            .onStart { setLoadingState() }
            .catch { throwable -> 
                if (throwable !is kotlinx.coroutines.CancellationException) {
                    setErrorState(throwable)
                }
            }
            .collect { translationList -> 
                val result = translationList.firstOrNull()?.translatedText ?: ""
                translationCache[cacheKey] = result
                lastTranslatedText = text
                setSuccessState(translationList)
            }
    }

    private fun setLoadingState() {
        updateState { state -> state.setLoading(true) }
    }

    private fun setErrorState(throwable: Throwable) {
        val message = throwable.localizedMessage ?: throwable.toString()
        updateState { state -> state.setError(message) }
    }

    private fun setSuccessState(translationList: List<Translation>) {
        val translatedText = translationList.firstOrNull()?.translatedText ?: ""
        updateState { state -> state.setResult(translatedText) }
    }
}