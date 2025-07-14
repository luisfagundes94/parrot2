package com.luisfagundes.parrot.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.luisfagundes.parrot.ui.ParrotAppState
import com.luisfagundes.dictionary.presentation.DictionaryRoute
import com.luisfagundes.dictionary.presentation.dictionaryScreen

@Composable
fun ParrotNavHost(
    appState: ParrotAppState,
    modifier: Modifier = Modifier,
) {
    val navController = appState.navController

    NavHost(
        navController = navController,
        startDestination = DictionaryRoute,
        modifier = modifier,
    ) {
        dictionaryScreen()
    }
}