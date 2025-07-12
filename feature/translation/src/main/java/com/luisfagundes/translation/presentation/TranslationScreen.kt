package com.luisfagundes.translation.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.luisfagundes.designsystem.theme.spacing
import com.luisfagundes.translation.presentation.components.LanguageSelector
import com.luisfagundes.translation.presentation.components.TranslationInputField
import com.luisfagundes.translation.presentation.components.TranslationResults

@Composable
internal fun TranslationRoute(
    viewModel: TranslationViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    TranslationScreen(
        uiState = uiState,
        onInputTextChange = { text ->
            viewModel.translate(text, targetLang = uiState.languagePair.second)
        },
        modifier = Modifier
            .padding(MaterialTheme.spacing.default)
            .fillMaxWidth()
    )
}

@Composable
internal fun TranslationScreen(
    uiState: TranslationUiState,
    modifier: Modifier = Modifier,
    onInputTextChange: (String) -> Unit,
) {
    val textStyle = MaterialTheme.typography.headlineMedium
    var inputText by remember { mutableStateOf("") }

    LaunchedEffect(inputText) {
        onInputTextChange(inputText)
    }

    Column(
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.default),
        modifier = modifier
    ) {
        LanguageSelector(
            sourceLang = uiState.languagePair.first.name,
            targetLang = uiState.languagePair.second.name,
            onSourceLangClick = {},
            onTargetLangClick = {},
            onLangSwitchClick = {},
            modifier = Modifier.fillMaxWidth()
        )
        TranslationInputField(
            value = inputText,
            onValueChange = { newText -> inputText = newText },
            textStyle = textStyle,
            modifier = Modifier.fillMaxWidth()
        )
        TranslationResults(
            uiState = uiState,
            textStyle = textStyle
        )
    }
}