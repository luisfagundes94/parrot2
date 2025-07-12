package com.luisfagundes.translation.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.luisfagundes.designsystem.theme.spacing
import com.luisfagundes.translation.R
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
        onTranslateButtonClick = viewModel::translate,
        modifier = Modifier
            .padding(MaterialTheme.spacing.default)
            .fillMaxWidth()
    )
}

@Composable
internal fun TranslationScreen(
    uiState: TranslationUiState,
    modifier: Modifier = Modifier,
    onTranslateButtonClick: (String) -> Unit,
) {
    val textStyle = MaterialTheme.typography.headlineMedium
    var inputText by remember { mutableStateOf("") }

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
        Button(
            onClick = { onTranslateButtonClick(inputText) }
        ) {
            Text(
                text = stringResource(R.string.translate)
            )
        }
        TranslationResults(
            uiState = uiState,
            textStyle = textStyle
        )
    }
}