package com.luisfagundes.translation.presentation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable data object TranslationRoute

fun NavController.navigateToTranslation(navOptions: NavOptions) =
    navigate(route = TranslationRoute, navOptions)

fun NavGraphBuilder.translationScreen() {
    composable<TranslationRoute> {
        TranslationRoute()
    }
}