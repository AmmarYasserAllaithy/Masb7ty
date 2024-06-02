package com.ammaryasser.masb7ty.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
)

fun Typography.defaultFontFamily(family: FontFamily): Typography = copy(
    displayLarge = displayLarge.copy(fontFamily = family, fontWeight = FontWeight.Bold),
    displayMedium = displayMedium.copy(fontFamily = family, fontWeight = FontWeight.Bold),
    displaySmall = displaySmall.copy(fontFamily = family, fontWeight = FontWeight.Bold),
    headlineLarge = headlineLarge.copy(fontFamily = family, fontWeight = FontWeight.Bold),
    headlineMedium = headlineMedium.copy(fontFamily = family, fontWeight = FontWeight.Bold),
    headlineSmall = headlineSmall.copy(fontFamily = family, fontWeight = FontWeight.Bold),
    titleLarge = titleLarge.copy(fontFamily = family, fontWeight = FontWeight.Bold),
    titleMedium = titleMedium.copy(fontFamily = family, fontWeight = FontWeight.Bold),
    titleSmall = titleSmall.copy(fontFamily = family, fontWeight = FontWeight.Bold),
    bodyLarge = bodyLarge.copy(fontFamily = family, fontWeight = FontWeight.Bold),
    bodyMedium = bodyMedium.copy(fontFamily = family, fontWeight = FontWeight.Bold),
    bodySmall = bodySmall.copy(fontFamily = family, fontWeight = FontWeight.Bold),
    labelLarge = labelLarge.copy(fontFamily = family, fontWeight = FontWeight.Bold),
    labelMedium = labelMedium.copy(fontFamily = family, fontWeight = FontWeight.Bold),
    labelSmall = labelSmall.copy(fontFamily = family, fontWeight = FontWeight.Bold)
)