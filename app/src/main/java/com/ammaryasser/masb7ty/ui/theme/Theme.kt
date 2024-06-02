package com.ammaryasser.masb7ty.ui.theme

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
    primary = Palette.Blue.color,
    secondary = Palette.Green.color,
    tertiary = Palette.Purple.color,
    background = Color(0xFF1C1B1F),
    surface = Color(0xFF2C2B2F),

    onPrimary = Color.White,
    onSecondary = Color.Black,
    onTertiary = Color.White,
    onBackground = Color(0xFFFFFBFE),
    onSurface = Color(0xFFEFEBEE),
)

private val LightColorScheme = lightColorScheme(
    primary = Palette.Blue.color,
    secondary = Palette.Green.color,
    tertiary = Palette.Purple.color,
    background = Color(255, 245, 250),
    surface = Color(240, 230, 235),

    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
)

@Composable
fun Masb7tyTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S ->
            with(LocalContext.current) {
                if (darkTheme) dynamicDarkColorScheme(this)
                else dynamicLightColorScheme(this)
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