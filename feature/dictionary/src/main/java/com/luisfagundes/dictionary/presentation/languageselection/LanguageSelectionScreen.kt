package com.luisfagundes.dictionary.presentation.languageselection

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import com.luisfagundes.common.extensions.isNotNull
import com.luisfagundes.designsystem.theme.LightAndDarkPreview
import com.luisfagundes.designsystem.theme.ParrotTheme
import com.luisfagundes.designsystem.theme.spacing
import com.luisfagundes.dictionary.R
import com.luisfagundes.dictionary.domain.model.SupportedLanguage

@Composable
internal fun LanguageSelectionScreen(
    onLanguageSaved: () -> Unit,
    viewModel: LanguageSelectionViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    
    LanguageSelectionScreen(
        uiState = uiState,
        onLanguageSelected = { language ->
            viewModel.saveLanguage(language = language)
            onLanguageSaved()
        },
        modifier = Modifier
            .fillMaxSize()
            .padding(MaterialTheme.spacing.default)
    )
}

@Composable
internal fun LanguageSelectionScreen(
    uiState: LanguageSelectionUiState,
    onLanguageSelected: (SupportedLanguage) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        SelectionHeader(
            uiState = uiState,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(MaterialTheme.spacing.default))

        LazyColumn(
            contentPadding = PaddingValues(vertical = MaterialTheme.spacing.small),
            verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small),
            modifier = Modifier.weight(1f)
        ) {
            items(SupportedLanguage.entries) { language ->
                LanguageItem(
                    language = language,
                    isSelected = uiState.currentSelectedLanguage == language,
                    onLanguageClick = { onLanguageSelected(language) }
                )
            }
        }
    }
}

@Composable
private fun SelectionHeader(
    uiState: LanguageSelectionUiState,
    modifier: Modifier = Modifier
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        modifier = modifier
    ) {
        Column(
            modifier = Modifier.padding(MaterialTheme.spacing.default)
        ) {
            Text(
                text = if (uiState.isSourceLanguage) {
                    stringResource(R.string.select_source_language)
                } else {
                    stringResource(R.string.select_target_language)
                },
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold
            )

            if (uiState.currentSelectedLanguage.isNotNull()) {
                Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        if (uiState.isSourceLanguage) {
                            Text(
                                text = "Source: ${uiState.currentSelectedLanguage.name}",
                                style = MaterialTheme.typography.bodyMedium
                            )
                        } else {
                            Text(
                                text = "Target: ${uiState.currentSelectedLanguage.name}",
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun LanguageItem(
    language: SupportedLanguage,
    isSelected: Boolean,
    onLanguageClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        onClick = onLanguageClick,
        colors = CardDefaults.cardColors(
            containerColor = if (isSelected) {
                MaterialTheme.colorScheme.primary
            } else {
                MaterialTheme.colorScheme.surfaceContainer
            }
        ),
        modifier = modifier.fillMaxWidth()
    ) {
        Text(
            text = language.name,
            style = MaterialTheme.typography.bodyLarge,
            color = if (isSelected) {
                MaterialTheme.colorScheme.onPrimary
            } else {
                MaterialTheme.colorScheme.onSurfaceVariant
            },
            modifier = Modifier.padding(MaterialTheme.spacing.default)
        )
    }
}

@Composable
@LightAndDarkPreview
internal fun LanguageSelectionScreenPreview() {
    ParrotTheme(dynamicColor = false) {
        Surface {
            LanguageSelectionScreen(
                uiState = LanguageSelectionUiState(
                    currentSelectedLanguage = SupportedLanguage.English,
                    isSourceLanguage = true
                ),
                onLanguageSelected = {},
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}