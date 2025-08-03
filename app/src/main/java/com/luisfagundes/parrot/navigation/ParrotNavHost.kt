package com.luisfagundes.parrot.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.luisfagundes.parrot.ui.ParrotAppState
import com.luisfagundes.dictionary.presentation.translation.navigation.TranslationRoute
import com.luisfagundes.dictionary.presentation.translation.navigation.translationScreen
import com.luisfagundes.dictionary.presentation.history.navigation.historyScreen
import com.luisfagundes.dictionary.presentation.languageselection.navigation.languageSelectionScreen
import com.luisfagundes.dictionary.presentation.languageselection.navigation.navigateToLanguageSelection

@Composable
fun ParrotNavHost(
    appState: ParrotAppState,
    modifier: Modifier = Modifier,
) {
    val navController = appState.navController

    NavHost(
        navController = navController,
        startDestination = TranslationRoute,
        modifier = modifier,
    ) {
        // Top Level Destinations
        translationScreen(
            onNavigateToLanguageSelection = { languageCode, isFromSource ->
                navController.navigateToLanguageSelection(
                    currentLanguageCode = languageCode,
                    isSourceLanguage = isFromSource
                )
            }
        )
        historyScreen()

        // Other
        languageSelectionScreen(
            onLanguageSaved = {
                navController.popBackStack()
            }
        )
    }
}