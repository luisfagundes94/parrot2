package com.luisfagundes.dictionary.presentation.history

import androidx.lifecycle.viewModelScope
import com.luisfagundes.common.dispatcher.AppDispatcher.IO
import com.luisfagundes.common.dispatcher.Dispatcher
import com.luisfagundes.common.presentation.ViewModel
import com.luisfagundes.common.provider.ResourceProvider
import com.luisfagundes.dictionary.domain.model.TranslationHistoryItem
import com.luisfagundes.dictionary.R
import com.luisfagundes.dictionary.domain.usecase.ClearAllHistoryUseCase
import com.luisfagundes.dictionary.domain.usecase.DeleteTranslationHistoryUseCase
import com.luisfagundes.dictionary.domain.usecase.GetTranslationHistoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class HistoryViewModel @Inject constructor(
    private val getTranslationHistoryUseCase: GetTranslationHistoryUseCase,
    private val deleteTranslationHistoryUseCase: DeleteTranslationHistoryUseCase,
    private val clearAllHistoryUseCase: ClearAllHistoryUseCase,
    private val resourceProvider: ResourceProvider,
    @param:Dispatcher(IO) private val dispatcher: CoroutineDispatcher
) : ViewModel<HistoryUiState>(
    initialState = HistoryUiState()
) {

    init {
        loadHistory()
    }

    fun loadHistory() {
        viewModelScope.launch {
            getTranslationHistoryUseCase()
                .flowOn(dispatcher)
                .onStart { setLoadingState() }
                .catch { throwable -> setErrorState(throwable.message.toString()) }
                .collect { historyItems -> setHistoryItems(historyItems) }
        }
    }

    fun deleteHistoryItem(id: Long) {
        viewModelScope.launch(dispatcher) {
            try {
                deleteTranslationHistoryUseCase(id)
            } catch (e: Exception) {
                setErrorState(resourceProvider.getString(R.string.delete_history_error))
            }
        }
    }

    fun showClearAllDialog() {
        updateState { state -> state.showClearAllDialog() }
    }

    fun hideClearAllDialog() {
        updateState { state -> state.hideClearAllDialog() }
    }

    fun clearAllHistory() {
        viewModelScope.launch(dispatcher) {
            try {
                clearAllHistoryUseCase()
                hideClearAllDialog()
            } catch (e: Exception) {
                setErrorState(resourceProvider.getString(R.string.clear_all_history_error))
                hideClearAllDialog()
            }
        }
    }

    private fun setLoadingState() {
        updateState { state -> state.setLoading(true) }
    }

    private fun setErrorState(message: String) {
        updateState { state -> state.setError(message) }
    }

    private fun setHistoryItems(items: List<TranslationHistoryItem>) {
        updateState { state -> state.setHistoryItems(items) }
    }
}