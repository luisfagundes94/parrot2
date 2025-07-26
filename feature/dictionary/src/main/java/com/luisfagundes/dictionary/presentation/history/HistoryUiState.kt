package com.luisfagundes.dictionary.presentation.history

import com.luisfagundes.common.presentation.UiState
import com.luisfagundes.dictionary.domain.model.WordHistory

internal data class HistoryUiState(
    val words: List<WordHistory> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String = "",
    val showClearAllDialog: Boolean = false
) : UiState {
    
    val isEmpty: Boolean
        get() = words.isEmpty() && !isLoading
    
    fun setLoading(loading: Boolean) = this.copy(
        isLoading = loading,
        errorMessage = ""
    )
    
    fun setWords(words: List<WordHistory>) = this.copy(
        words = words,
        isLoading = false,
        errorMessage = ""
    )
    
    fun setError(message: String) = this.copy(
        isLoading = false,
        errorMessage = message
    )
    
    fun showClearAllDialog() = this.copy(showClearAllDialog = true)
    
    fun hideClearAllDialog() = this.copy(showClearAllDialog = false)
}