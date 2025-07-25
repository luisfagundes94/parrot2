package com.luisfagundes.parrot.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.luisfagundes.parrot.navigation.TopLevelDestination
import com.luisfagundes.dictionary.presentation.translation.navigateToTranslation
import kotlinx.coroutines.CoroutineScope

@Composable
fun rememberParrotAppState(
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    navController: NavHostController = rememberNavController(),
): ParrotAppState {
    return remember(
        navController,
        coroutineScope
    ) {
        ParrotAppState(
            navController = navController,
            coroutineScope = coroutineScope
        )
    }
}

@Stable
class ParrotAppState(
    val navController: NavHostController,
    coroutineScope: CoroutineScope
) {
    private val previousDestination = mutableStateOf<NavDestination?>(null)

    val currentDestination: NavDestination?
        @Composable get() {
            val currentEntry = navController.currentBackStackEntryFlow
                .collectAsState(initial = null)

            return currentEntry.value?.destination.also { destination ->
                if (destination != null) {
                    previousDestination.value = destination
                }
            } ?: previousDestination.value
        }

    val currentTopLevelDestination: TopLevelDestination?
        @Composable get() {
            return TopLevelDestination.entries.firstOrNull { topLevelDestination ->
                currentDestination?.hasRoute(route = topLevelDestination.route) == true
            }
        }

    val topLevelDestinations: List<TopLevelDestination> = TopLevelDestination.entries

    val shouldShowBottomBar: Boolean
        @Composable get() = currentTopLevelDestination != null

    fun navigateToTopLevelDestination(topLevelDestination: TopLevelDestination) {
        val topLevelNavOptions = navOptions {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }

        when (topLevelDestination) {
            TopLevelDestination.HOME -> navController.navigateToTranslation(topLevelNavOptions)
        }
    }
}