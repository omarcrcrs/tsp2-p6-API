package com.example.e7_api.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = LechuzaPrimary80,
    onPrimary = LechuzaOnPrimary80,
    secondary = LechuzaSecondary80,
    tertiary = LechuzaAmbarClaro,
    background = LechuzaBackgroundDark,
    surface = LechuzaBackgroundDark,
    onBackground = LechuzaOnSurfaceDark,
    onSurface = LechuzaOnSurfaceDark,
    surfaceVariant = LechuzaSurfaceVariantDark,
    onSurfaceVariant = LechuzaOnSurfaceVariantDark,
    error = LechuzaErrorDark
)

private val LightColorScheme = lightColorScheme(
    primary = LechuzaPrimary40,
    onPrimary = LechuzaOnPrimary40,
    secondary = LechuzaSecondary40,
    tertiary = LechuzaAmbar,
    background = LechuzaBackground,
    surface = LechuzaBackground,
    onBackground = LechuzaOnSurface,
    onSurface = LechuzaOnSurface,
    surfaceVariant = LechuzaSurfaceVariant,
    onSurfaceVariant = LechuzaOnSurfaceVariant,
    error = LechuzaError
)

@Composable
fun E7_apiTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
