package com.luisfagundes.parrot


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import com.luisfagundes.designsystem.theme.ParrotTheme
import com.luisfagundes.parrot.ui.ParrotApp
import com.luisfagundes.parrot.ui.rememberParrotAppState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val appState = rememberParrotAppState()

            ParrotTheme(
                darkTheme = isSystemInDarkTheme(),
                dynamicColor = false
            ) {
                ParrotApp(
                    appState = appState,
                )
            }
        }
    }
}