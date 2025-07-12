package com.luisfagundes.translation.presentation.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.SwapHoriz
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.luisfagundes.designsystem.theme.ParrotTheme
import com.luisfagundes.designsystem.theme.LightAndDarkPreview
import com.luisfagundes.translation.R

@Composable
internal fun LanguageSelector(
    sourceLang: String,
    targetLang: String,
    onSourceLangClick: () -> Unit,
    onTargetLangClick: () -> Unit,
    onLangSwitchClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        OutlinedButton(
            onClick = onSourceLangClick,
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = sourceLang
            )
        }
        IconButton(
            onClick = onLangSwitchClick
        ) {
            Icon(
                imageVector = Icons.Default.SwapHoriz,
                contentDescription = stringResource(R.string.swap_languages)
            )
        }
        OutlinedButton(
            onClick = onTargetLangClick,
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = targetLang
            )
        }
    }
}

@Composable
@LightAndDarkPreview
internal fun LanguagePairSwitcherPreview() {
    ParrotTheme(
        dynamicColor = false
    ) {
        Surface {
            LanguageSelector(
                sourceLang = "English",
                targetLang = "Portuguese",
                onSourceLangClick = {},
                onTargetLangClick = {},
                onLangSwitchClick = {},
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}