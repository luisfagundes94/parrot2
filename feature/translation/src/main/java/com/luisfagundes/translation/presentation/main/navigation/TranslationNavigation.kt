package com.luisfagundes.translation.presentation.main.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.luisfagundes.translation.presentation.main.TranslationRoute
import kotlinx.serialization.Serializable

@Serializable data object TranslationRoute

fun NavController.navigateToTranslation(navOptions: NavOptions) =
    navigate(route = TranslationRoute, navOptions)

fun NavGraphBuilder.translationScreen(
    onNavigateToLanguageSelection: (languageCode: String, isSourceLanguage: Boolean) -> Unit
) {
    composable<TranslationRoute> {
        TranslationRoute(
            onNavigateToLanguageSelection = onNavigateToLanguageSelection
        )
    }
}