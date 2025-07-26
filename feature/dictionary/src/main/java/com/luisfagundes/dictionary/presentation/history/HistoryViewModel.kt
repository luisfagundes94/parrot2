package com.luisfagundes.dictionary.presentation.history

import androidx.lifecycle.viewModelScope
import com.luisfagundes.common.dispatcher.AppDispatcher.IO
import com.luisfagundes.common.dispatcher.Dispatcher
import com.luisfagundes.common.presentation.ViewModel
import com.luisfagundes.common.provider.ResourceProvider
import com.luisfagundes.dictionary.domain.model.WordHistory
import com.luisfagundes.dictionary.R
import com.luisfagundes.dictionary.domain.usecase.ClearAllSavedWordsUseCase
import com.luisfagundes.dictionary.domain.usecase.DeleteWordUseCase
import com.luisfagundes.dictionary.domain.usecase.GetSavedWordsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class HistoryViewModel @Inject constructor(
    private val getSavedWordsUseCase: GetSavedWordsUseCase,
    private val deleteWordUseCase: DeleteWordUseCase,
    private val clearAllSavedWordsUseCase: ClearAllSavedWordsUseCase,
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
            getSavedWordsUseCase()
                .flowOn(dispatcher)
                .onStart { setLoadingState() }
                .catch { throwable -> setErrorState(throwable.message.toString()) }
                .collect { savedWords -> setSavedWords(savedWords) }
        }
    }

    fun deleteWord(id: Long) {
        viewModelScope.launch(dispatcher) {
            try {
                deleteWordUseCase(id)
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
                clearAllSavedWordsUseCase()
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

    private fun setSavedWords(words: List<WordHistory>) {
        updateState { state -> state.setSavedWords(words) }
    }
}