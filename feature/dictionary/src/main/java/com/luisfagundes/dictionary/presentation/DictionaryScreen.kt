package com.luisfagundes.dictionary.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.luisfagundes.designsystem.theme.spacing
import com.luisfagundes.dictionary.R
import com.luisfagundes.dictionary.presentation.components.LanguageSelector
import com.luisfagundes.designsystem.component.ParrotTextField
import com.luisfagundes.dictionary.presentation.components.DictionaryResult

@Composable
internal fun DictionaryRoute(
    viewModel: DictionaryViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    DictionaryScreen(
        uiState = uiState,
        onTranslateButtonClick = viewModel::translate,
        modifier = Modifier
            .padding(MaterialTheme.spacing.default)
            .fillMaxWidth()
    )
}

@Composable
internal fun DictionaryScreen(
    uiState: DictionaryUiState,
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
                text = stringResource(R.string.translate)
            )
        }
        DictionaryResult(
            uiState.words
        )
    }
}