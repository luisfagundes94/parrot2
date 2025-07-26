package com.luisfagundes.dictionary.presentation.translation.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BookmarkAdd
import androidx.compose.material.icons.filled.BookmarkAdded
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.stringResource
import com.luisfagundes.dictionary.R

@Composable
fun SaveWordButton(
    isWordSaved: Boolean,
    onSaveWordClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    IconButton(
        onClick = { if (isWordSaved.not()) onSaveWordClick() },
        modifier = modifier
    ) {
        when {
            isWordSaved -> {
                Icon(
                    modifier = Modifier.scale(1.1f),
                    imageVector = Icons.Default.BookmarkAdded,
                    contentDescription = stringResource(R.string.word_saved_to_history),
                    tint = MaterialTheme.colorScheme.primary
                )
            }
            else -> {
                Icon(
                    modifier = Modifier.scale(1.1f),
                    imageVector = Icons.Default.BookmarkAdd,
                    contentDescription = stringResource(R.string.desc_bookmark_word),
                )
            }
        }
    }
}
