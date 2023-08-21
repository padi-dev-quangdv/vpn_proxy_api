package com.midterm.securevpnproxy.base.compose

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.LocalMinimumInteractiveComponentEnforcement
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AppTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
//    val color = if (darkTheme) {
//        DarkColorPalette
//    } else {
//        LightColorPalette
//    }
//    val customColorsPalette = if (darkTheme) {
//        AppDarkColorsPalette
//    } else {
//        AppLightColorsPalette
//    }
//    val customTextSelectionColors = if (darkTheme) {
//        DarkTextSelectionColors
//    } else {
//        LightTextSelectionColors
//    }

    MaterialTheme(
        colors = LightColorPalette,
        typography = appTypography,
        shapes = Shapes,
    ) {
        CompositionLocalProvider(
            LocalColors provides AppLightColorsPalette,
            LocalTextSelectionColors provides LightTextSelectionColors,
            LocalMinimumInteractiveComponentEnforcement provides false,
            content = content
        )
    }
}