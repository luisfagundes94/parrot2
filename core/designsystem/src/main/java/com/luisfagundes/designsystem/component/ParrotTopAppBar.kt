package com.luisfagundes.designsystem.component

import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import com.luisfagundes.designsystem.theme.ParrotTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ParrotTopAppBar(
    title: String,
    colors: TopAppBarColors = TopAppBarDefaults.centerAlignedTopAppBarColors(
        containerColor = Color.Transparent
    ),
    navigationIcon: ImageVector = Icons.AutoMirrored.Default.ArrowBack,
    navigationIconContentDescription: String? = null,
    onNavigationClick: () -> Unit = {},
    content: @Composable () -> Unit = {}
) {
    Column {
        TopAppBar(
            title = { Text(title) },
            navigationIcon = {
                IconButton(onClick = onNavigationClick) {
                    Icon(
                        imageVector = navigationIcon,
                        contentDescription = navigationIconContentDescription,
                        tint = MaterialTheme.colorScheme.onSurface,
                    )
                }
            },
            colors = colors
        )
        content.invoke()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview("Top App Bar")
@Composable
private fun ParrotTopAppBarPreview() {
    ParrotTheme {
        ParrotTopAppBar(
            title = "Home",
        )
    }
}