package com.luisfagundes.parrot.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Translate
import androidx.compose.material.icons.outlined.History
import androidx.compose.material.icons.outlined.Translate
import androidx.compose.ui.graphics.vector.ImageVector
import com.luisfagundes.dictionary.presentation.translation.navigation.TranslationRoute
import com.luisfagundes.dictionary.presentation.history.navigation.HistoryRoute
import kotlin.reflect.KClass

enum class TopLevelDestination(
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    @param:StringRes val iconTextId: Int,
    @param:StringRes val titleTextId: Int,
    val route: KClass<*>,
) {
    HOME(
        selectedIcon = Icons.Filled.Translate,
        unselectedIcon = Icons.Outlined.Translate,
        iconTextId = com.luisfagundes.dictionary.R.string.feature_translation_title,
        titleTextId = com.luisfagundes.dictionary.R.string.feature_translation_title,
        route = TranslationRoute::class,
    ),
    HISTORY(
        selectedIcon = Icons.Filled.History,
        unselectedIcon = Icons.Outlined.History,
        iconTextId = com.luisfagundes.dictionary.R.string.history_title,
        titleTextId = com.luisfagundes.dictionary.R.string.history_title,
        route = HistoryRoute::class,
    ),
}