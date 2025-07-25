package com.luisfagundes.dictionary.presentation.history

import com.luisfagundes.common.presentation.UiState
import com.luisfagundes.dictionary.domain.model.TranslationHistoryItem

internal data class HistoryUiState(
    val historyItems: List<TranslationHistoryItem> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String = "",
    val showClearAllDialog: Boolean = false
) : UiState {
    
    val isEmpty: Boolean
        get() = historyItems.isEmpty() && !isLoading
    
    fun setLoading(loading: Boolean) = this.copy(
        isLoading = loading,
        errorMessage = ""
    )
    
    fun setHistoryItems(items: List<TranslationHistoryItem>) = this.copy(
        historyItems = items,
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