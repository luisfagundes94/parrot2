package com.luisfagundes.dictionary.presentation.languageselection

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.luisfagundes.designsystem.theme.LightAndDarkPreview
import com.luisfagundes.designsystem.theme.ParrotTheme
import com.luisfagundes.designsystem.theme.spacing
import com.luisfagundes.dictionary.R
import com.luisfagundes.dictionary.domain.model.SupportedLanguage

@Composable
internal fun LanguageSelectionRoute(
    onLanguageSelected: (SupportedLanguage) -> Unit
) {
    LanguageSelectionScreen(
        onLanguageSelected = onLanguageSelected,
        modifier = Modifier
            .fillMaxSize()
            .padding(MaterialTheme.spacing.default)
    )
}

@Composable
internal fun LanguageSelectionScreen(
    onLanguageSelected: (SupportedLanguage) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        contentPadding = PaddingValues(vertical = MaterialTheme.spacing.small),
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small),
        modifier = modifier
    ) {
        items(SupportedLanguage.entries) { language ->
            LanguageItem(
                language = language,
                onLanguageClick = { onLanguageSelected(language) }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun LanguageItem(
    language: SupportedLanguage,
    onLanguageClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        onClick = onLanguageClick,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        ),
        modifier = modifier.fillMaxWidth()
    ) {
        Text(
            text = language.name,
            style = MaterialTheme.typography.bodyLarge,
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
                onLanguageSelected = {},
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}