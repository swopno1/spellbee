package com.example.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = HoneyGold,
    onPrimary = HiveDarkSlate,
    primaryContainer = HoneyDark,
    onPrimaryContainer = HoneyCream,
    secondary = SkyBlue,
    onSecondary = Color.White,
    secondaryContainer = SkyBlueDark,
    onSecondaryContainer = SkyBlueLight,
    tertiary = MeadowGreen,
    onTertiary = Color.White,
    background = HiveDarkSlate,
    onBackground = Color.White,
    surface = HiveCardDark,
    onSurface = Color.White,
    surfaceVariant = HiveSurfaceDark,
    onSurfaceVariant = Color(0xFFCBD5E1)
)

private val LightColorScheme = lightColorScheme(
    primary = HoneyGold,
    onPrimary = Color.White,
    primaryContainer = HoneyCream,
    onPrimaryContainer = HoneyDark,
    secondary = SkyBlue,
    onSecondary = Color.White,
    secondaryContainer = SkyBlueLight,
    onSecondaryContainer = SkyBlueDark,
    tertiary = MeadowGreen,
    onTertiary = Color.White,
    background = BeeWarmBackground,
    onBackground = TextDark,
    surface = BeeCardSurface,
    onSurface = TextDark,
    surfaceVariant = Color(0xFFF1F5F9),
    onSurfaceVariant = TextMuted
)

@Composable
fun SpellBeeTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false, // Keep cheerful custom palette by default
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
