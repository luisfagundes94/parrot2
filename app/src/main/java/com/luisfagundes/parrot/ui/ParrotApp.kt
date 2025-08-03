package com.luisfagundes.parrot.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.WindowAdaptiveInfo
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTagsAsResourceId
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import com.luisfagundes.designsystem.component.ParrotNavigationSuiteScaffold
import com.luisfagundes.designsystem.component.ParrotTopAppBar
import com.luisfagundes.parrot.R
import com.luisfagundes.parrot.navigation.ParrotNavHost
import kotlin.reflect.KClass

@Composable
fun ParrotApp(
    appState: ParrotAppState,
    modifier: Modifier = Modifier,
    windowAdaptiveInfo: WindowAdaptiveInfo = currentWindowAdaptiveInfo()
) {
    InternalParrotApp(
        appState = appState,
        modifier = modifier,
        windowAdaptiveInfo = windowAdaptiveInfo
    )
}

@Composable
@OptIn(
    ExperimentalMaterial3Api::class,
    ExperimentalComposeUiApi::class,
)
internal fun InternalParrotApp(
    appState: ParrotAppState,
    modifier: Modifier = Modifier,
    windowAdaptiveInfo: WindowAdaptiveInfo = currentWindowAdaptiveInfo(),
) {
    val currentDestination = appState.currentDestination
    val isTopLevelDestination = appState.isTopLevelDestination

    if (isTopLevelDestination) {
        ParrotNavigationSuiteScaffold(
            navigationSuiteItems = {
                appState.topLevelDestinations.forEach { destination ->
                    val selected = currentDestination.isRouteInHierarchy(destination.route)
                    item(
                        selected = selected,
                        onClick = { appState.navigateToTopLevelDestination(destination) },
                        icon = {
                            Icon(
                                imageVector = destination.unselectedIcon,
                                contentDescription = null,
                            )
                        },
                        selectedIcon = {
                            Icon(
                                imageVector = destination.selectedIcon,
                                contentDescription = null,
                            )
                        },
                        label = { Text(stringResource(destination.iconTextId)) },
                        modifier = Modifier.testTag("ParrotNavItem")
                    )
                }
            },
            windowAdaptiveInfo = windowAdaptiveInfo,
        ) {
            ParrotScaffoldContent(
                appState = appState,
                modifier = modifier
            )
        }
    } else {
        ParrotScaffoldContent(
            appState = appState,
            modifier = modifier
        )
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
private fun ParrotScaffoldContent(
    appState: ParrotAppState,
    modifier: Modifier = Modifier
) {
    val currentDestination = appState.currentDestination
    
    Scaffold(
        modifier = modifier.semantics { testTagsAsResourceId = true },
        containerColor = MaterialTheme.colorScheme.background,
        contentColor = MaterialTheme.colorScheme.onBackground,
        contentWindowInsets = WindowInsets(0, 0, 0, 0),
    ) { padding ->
        Column(
            Modifier
                .fillMaxSize()
                .padding(padding)
                .consumeWindowInsets(padding)
                .windowInsetsPadding(
                    WindowInsets.safeDrawing.only(
                        WindowInsetsSides.Horizontal + WindowInsetsSides.Top
                    ),
                ),
        ) {
            val destination = appState.currentTopLevelDestination
            val isTopLevelDestination = appState.topLevelDestinations.any { destination ->
                currentDestination.isRouteInHierarchy(destination.route)
            }
            val shouldShowTopAppBar = currentDestination != null && isTopLevelDestination.not()

            if (shouldShowTopAppBar) {
                ParrotTopAppBar(
                    title = stringResource(
                        destination?.titleTextId ?: R.string.app_name
                    ),
                    navigationIcon = Icons.AutoMirrored.Default.ArrowBack,
                    navigationIconContentDescription = stringResource(
                        id = R.string.arrow_back_content_description
                    ),
                    onNavigationClick = { appState.navController.popBackStack() }
                )
            }

            ParrotNavHost(
                appState = appState,
            )
        }
    }
}

private fun NavDestination?.isRouteInHierarchy(route: KClass<*>) =
    this?.hierarchy?.any { it.hasRoute(route) } == true