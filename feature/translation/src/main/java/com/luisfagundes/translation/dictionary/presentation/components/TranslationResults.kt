package com.luisfagundes.translation.dictionary.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import com.luisfagundes.designsystem.theme.spacing
import com.luisfagundes.translation.presentation.TranslationUiState

@Composable
internal fun TranslationResults(
    uiState: TranslationUiState,
    textStyle: TextStyle,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.default),
        modifier = modifier
    ) {
        if (uiState.isLoading) {
            CircularProgressIndicator()
        }

        if (uiState.errorMessage.isNotBlank()) {
            Text(
                text = uiState.errorMessage,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodyLarge
            )
        }

        if (uiState.translatedText.isNotBlank()) {
            Text(
                text = uiState.translatedText,
                style = textStyle,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}