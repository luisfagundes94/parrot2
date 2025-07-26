package com.luisfagundes.dictionary.presentation.history

import androidx.lifecycle.viewModelScope
import com.luisfagundes.common.dispatcher.AppDispatcher.IO
import com.luisfagundes.common.dispatcher.Dispatcher
import com.luisfagundes.common.presentation.ViewModel
import com.luisfagundes.common.provider.ResourceProvider
import com.luisfagundes.dictionary.domain.model.WordHistory
import com.luisfagundes.dictionary.R
import com.luisfagundes.dictionary.domain.usecase.ClearWordHistoryUseCase
import com.luisfagundes.dictionary.domain.usecase.DeleteWordFromHistoryUseCase
import com.luisfagundes.dictionary.domain.usecase.GetWordHistoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class HistoryViewModel @Inject constructor(
    private val getWordHistoryUseCase: GetWordHistoryUseCase,
    private val deleteWordFromHistoryUseCase: DeleteWordFromHistoryUseCase,
    private val clearWordHistoryUseCase: ClearWordHistoryUseCase,
    private val resourceProvider: ResourceProvider,
    @param:Dispatcher(IO) private val dispatcher: CoroutineDispatcher
) : ViewModel<HistoryUiState>(
    initialState = HistoryUiState()
) {

    init {
        loadWordHistory()
    }

    fun loadWordHistory() {
        viewModelScope.launch {
            getWordHistoryUseCase()
                .flowOn(dispatcher)
                .onStart { setLoadingState() }
                .catch { throwable -> setErrorState(throwable.message.toString()) }
                .collect { savedWords -> setWords(savedWords) }
        }
    }

    fun deleteWordFromHistory(id: Long) {
        viewModelScope.launch(dispatcher) {
            try {
                deleteWordFromHistoryUseCase(id)
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
                clearWordHistoryUseCase()
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

    private fun setWords(words: List<WordHistory>) {
        updateState { state -> state.setWords(words) }
    }
}