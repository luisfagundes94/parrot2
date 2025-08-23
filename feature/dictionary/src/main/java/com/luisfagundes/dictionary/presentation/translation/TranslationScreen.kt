package com.luisfagundes.dictionary.presentation.translation

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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.luisfagundes.designsystem.component.ParrotTextField
import com.luisfagundes.designsystem.theme.spacing
import com.luisfagundes.dictionary.R
import com.luisfagundes.dictionary.domain.model.Word
import com.luisfagundes.dictionary.presentation.translation.components.ExamplesResult
import com.luisfagundes.dictionary.presentation.translation.components.LanguageSelector
import com.luisfagundes.dictionary.presentation.translation.components.WordReminderDialog
import com.luisfagundes.dictionary.presentation.translation.components.TranslationsResult

@Composable
internal fun TranslationRoute(
    onNavigateToLanguageSelection: (languageCode: String, isSourceLanguage: Boolean) -> Unit,
    viewModel: TranslationViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val scrollState = rememberScrollState()

    TranslationScreen(
        uiState = uiState,
        onLanguageButtonClick = { languageCode, isSourceLanguage ->
            onNavigateToLanguageSelection(languageCode, isSourceLanguage)
        },
        onTranslateButtonClick = viewModel::translate,
        onLanguageSwapButtonClick = viewModel::swapLanguagePair,
        onSaveWordClick = viewModel::addWordToHistory,
        onInputTextChange = viewModel::updateInputText,
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
    onLanguageButtonClick: (languageCode: String, isSourceLanguage: Boolean) -> Unit,
    onTranslateButtonClick: (String) -> Unit,
    onLanguageSwapButtonClick: () -> Unit,
    onSaveWordClick: (Word, Boolean) -> Unit,
    onInputTextChange: (String) -> Unit,
) {
    val textStyle = MaterialTheme.typography.headlineMedium
    var showWordReminderDialog by rememberSaveable { mutableStateOf(false) }

    if (showWordReminderDialog) {
        WordReminderDialog(
            onDismissRequest = { showWordReminderDialog = false },
            onSetReminder = { _, _ -> }
        )
    }

    Column(
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.default),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        val sourceLanguage = uiState.languagePair.sourceLanguage
        val targetLanguage = uiState.languagePair.targetLanguage

        LanguageSelector(
            sourceLang = sourceLanguage.name,
            targetLang = targetLanguage.name,
            onSourceLanguageButtonClick = { onLanguageButtonClick(sourceLanguage.code, true) },
            onTargetLanguageButtonClick = { onLanguageButtonClick(targetLanguage.code, false) },
            onLanguageSwapButtonClick = onLanguageSwapButtonClick,
            modifier = Modifier.fillMaxWidth()
        )
        ParrotTextField(
            value = uiState.inputText,
            onValueChange = { newText -> onInputTextChange(newText) },
            textStyle = textStyle,
            modifier = Modifier
                .padding(vertical = MaterialTheme.spacing.default)
                .fillMaxWidth()
        )
        Button(
            onClick = { onTranslateButtonClick(uiState.inputText) },
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
                words = uiState.words,
                isWordInHistory = uiState.isWordInHistory,
                onSaveWordClick = {
                    showWordReminderDialog = true
                    onSaveWordClick(
                        uiState.words.first(),
                        uiState.isWordInHistory
                    )
                }
            )
            ExamplesResult(
                words = uiState.words,
                hasExamples = uiState.hasExamples
            )
        }
    }
}