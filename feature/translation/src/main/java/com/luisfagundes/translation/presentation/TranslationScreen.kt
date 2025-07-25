package com.luisfagundes.translation.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.luisfagundes.designsystem.component.ParrotTextField
import com.luisfagundes.designsystem.theme.spacing
import com.luisfagundes.translation.R
import com.luisfagundes.translation.presentation.components.ExamplesResult
import com.luisfagundes.translation.presentation.components.LanguageSelector
import com.luisfagundes.translation.presentation.components.TranslationsResult

@Composable
internal fun TranslationRoute(
    viewModel: TranslationViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val scrollState = rememberScrollState()

    TranslationScreen(
        uiState = uiState,
        onTranslateButtonClick = viewModel::translate,
        onLanguageSwapButtonClick = viewModel::swapLanguagePair,
        modifier = Modifier
            .verticalScroll(scrollState)
            .padding(MaterialTheme.spacing.default)
            .fillMaxWidth()
    )
}

@Composable
internal fun TranslationScreen(
    uiState: TranslationUiState,
    modifier: Modifier = Modifier,
    onTranslateButtonClick: (String) -> Unit,
    onLanguageSwapButtonClick: () -> Unit,
) {
    val textStyle = MaterialTheme.typography.headlineMedium
    var inputText by remember { mutableStateOf("") }

    Column(
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.default),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        LanguageSelector(
            sourceLang = uiState.languagePair.first.name,
            targetLang = uiState.languagePair.second.name,
            onSourceLanguageButtonClick = {},
            onTargetLanguageButtonClick = {},
            onLanguageSwapButtonClick = onLanguageSwapButtonClick,
            modifier = Modifier.fillMaxWidth()
        )
        ParrotTextField(
            value = inputText,
            onValueChange = { newText -> inputText = newText },
            textStyle = textStyle,
            modifier = Modifier
                .padding(vertical = MaterialTheme.spacing.default)
                .fillMaxWidth()
        )
        Button(
            onClick = { onTranslateButtonClick(inputText) },
            modifier = Modifier
                .height(48.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = stringResource(R.string.translate),
                style = MaterialTheme.typography.titleMedium
            )
        }
        if (uiState.isLoading) {
            CircularProgressIndicator()
        }
        if (uiState.errorMessage.isNotEmpty()) {
            Text(
                text = uiState.errorMessage,
                color = MaterialTheme.colorScheme.error
            )
        }
        if (uiState.words.isNotEmpty()) {
            TranslationsResult(
                words = uiState.words
            )
            ExamplesResult(
                words = uiState.words,
                hasExamples = uiState.hasExamples
            )
        }
    }
}