package com.luisfagundes.translation.presentation.history

import androidx.lifecycle.viewModelScope
import com.luisfagundes.common.dispatcher.AppDispatcher.IO
import com.luisfagundes.common.dispatcher.Dispatcher
import com.luisfagundes.common.presentation.ViewModel
import com.luisfagundes.common.provider.ResourceProvider
import com.luisfagundes.translation.domain.model.SavedWord
import com.luisfagundes.translation.R
import com.luisfagundes.translation.domain.usecase.ClearAllSavedWordsUseCase
import com.luisfagundes.translation.domain.usecase.DeleteSavedWordUseCase
import com.luisfagundes.translation.domain.usecase.GetAllSavedWordsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class HistoryViewModel @Inject constructor(
    private val getAllSavedWordsUseCase: GetAllSavedWordsUseCase,
    private val deleteSavedWordUseCase: DeleteSavedWordUseCase,
    private val clearAllSavedWordsUseCase: ClearAllSavedWordsUseCase,
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
            getAllSavedWordsUseCase()
                .flowOn(dispatcher)
                .onStart { setLoadingState() }
                .catch { throwable -> setErrorState(throwable.message.toString()) }
                .collect { savedWords -> setWords(savedWords) }
        }
    }

    fun deleteWordFromHistory(id: Long) {
        viewModelScope.launch(dispatcher) {
            try {
                deleteSavedWordUseCase(id)
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

    private fun setWords(words: List<SavedWord>) {
        updateState { state -> state.setWords(words) }
    }
}