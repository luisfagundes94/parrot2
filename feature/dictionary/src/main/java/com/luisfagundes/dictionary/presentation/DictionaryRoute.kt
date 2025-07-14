package com.luisfagundes.dictionary.presentation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable data object DictionaryRoute

fun NavController.navigateToDictionary(navOptions: NavOptions) =
    navigate(route = DictionaryRoute, navOptions)

fun NavGraphBuilder.dictionaryScreen() {
    composable<DictionaryRoute> {
        DictionaryRoute()
    }
}