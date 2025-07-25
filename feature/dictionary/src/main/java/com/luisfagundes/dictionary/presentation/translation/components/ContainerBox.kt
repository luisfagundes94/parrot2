package com.luisfagundes.dictionary.presentation.translation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.luisfagundes.designsystem.theme.spacing

@Composable
internal fun ContainerBox(
    container: @Composable () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colorScheme.surface,
                shape = MaterialTheme.shapes.small,
            )
            .border(
                width = 2.dp,
                color = MaterialTheme.colorScheme.onSurface,
                shape = MaterialTheme.shapes.small,
            )
            .padding(MaterialTheme.spacing.default),
    ) {
        container.invoke()
    }
}