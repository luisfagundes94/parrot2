package com.luisfagundes.translation.presentation.languageselection.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.luisfagundes.translation.presentation.languageselection.LanguageSelectionScreen
import kotlinx.serialization.Serializable

@Serializable
data class LanguageSelectionRoute(
    val currentLanguageCode: String,
    val isSourceLanguage: Boolean
)

fun NavController.navigateToLanguageSelection(
    currentLanguageCode: String,
    isSourceLanguage: Boolean,
    navOptions: NavOptions? = null
) {
    navigate(
        route = LanguageSelectionRoute(
            currentLanguageCode = currentLanguageCode,
            isSourceLanguage = isSourceLanguage
        ),
        navOptions = navOptions
    )
}

fun NavGraphBuilder.languageSelectionScreen(
    onLanguageSaved: () -> Unit = {}
) {
    composable<LanguageSelectionRoute> { backStackEntry ->
        LanguageSelectionScreen(
            onLanguageSaved = onLanguageSaved
        )
    }
}