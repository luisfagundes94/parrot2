package com.luisfagundes.parrot.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.luisfagundes.parrot.ui.ParrotAppState
import com.luisfagundes.dictionary.presentation.translation.navigation.TranslationRoute
import com.luisfagundes.dictionary.presentation.translation.navigation.translationScreen

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
        translationScreen()
    }
}