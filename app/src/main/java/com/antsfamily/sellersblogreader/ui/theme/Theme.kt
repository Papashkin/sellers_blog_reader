package com.antsfamily.sellersblogreader.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.ViewCompat

private val LightColors = lightColors(
    primary = primaryColor,
    onPrimary = onPrimaryColor,
    secondary = secondaryColor,
    onSecondary = textColor,
    background = backgroundColor,
    onBackground = textColor,
    surface = surfaceColor,
    onSurface = textColor,
    onError = onErrorColor,
    error = errorColor
)

private val DarkColors = darkColors(
    primary = darkPrimaryColor,
    onPrimary = darkOnPrimaryColor,
    secondary = darkSecondaryColor,
    onSecondary = darkTextColor,
    background = darkBackgroundColor,
    onBackground = darkTextColor,
    surface = darkSurfaceColor,
    onSurface = darkTextColor,
    onError = darkOnErrorColor,
    error = darkErrorColor
)

@Composable
fun SellersBlogReaderTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        darkTheme -> DarkColors
        else -> LightColors
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            (view.context as Activity).window.statusBarColor = colorScheme.primary.toArgb()
            ViewCompat.getWindowInsetsController(view)?.isAppearanceLightStatusBars = darkTheme
        }
    }

    MaterialTheme(
        colors = colorScheme,
        typography = Typography,
        content = content
    )
}
