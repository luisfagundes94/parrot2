package com.luisfagundes.translation.presentation.history.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.luisfagundes.translation.presentation.history.HistoryRoute
import kotlinx.serialization.Serializable

@Serializable data object HistoryRoute

fun NavController.navigateToHistory(navOptions: NavOptions? = null) {
    navigate(route = HistoryRoute, navOptions = navOptions)
}

fun NavGraphBuilder.historyScreen() {
    composable<HistoryRoute> {
        HistoryRoute()
    }
}