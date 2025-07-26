package com.luisfagundes.dictionary.presentation.translation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BookmarkAdd
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import com.luisfagundes.common.extensions.capitalize
import com.luisfagundes.designsystem.theme.spacing
import com.luisfagundes.dictionary.R
import com.luisfagundes.dictionary.domain.model.Word

@Composable
internal fun TranslationsResult(
    words: List<Word>,
    isWordInHistory: Boolean,
    onSaveWordClick: () -> Unit
) {
    ContainerBox {
        Row(
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = words.first().translations.first().text,
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = Bold,
            )
            Spacer(modifier = Modifier.weight(1f))
            SaveWordButton(
                isWordInHistory = isWordInHistory,
                onSaveWordClick = onSaveWordClick
            )
        }
        Spacer(
            modifier = Modifier.padding(vertical = MaterialTheme.spacing.verySmall),
        )
        Text(
            text = stringResource(R.string.other_translations),
            style = MaterialTheme.typography.titleMedium,
            fontWeight = Bold
        )
        Spacer(Modifier.height(MaterialTheme.spacing.small))
        words.forEach { word ->
            Text(
                text = word.translations.first().partOfSpeech.capitalize(),
                fontWeight = Bold,
                color = MaterialTheme.colorScheme.primary,
            )
            Spacer(Modifier.height(MaterialTheme.spacing.verySmall))
            Row(
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(bottom = MaterialTheme.spacing.verySmall),
            ) {
                word.translations.joinToString(", ") { it.text }.let { text ->
                    Text(
                        text = text
                    )
                }
            }
        }
    }
}