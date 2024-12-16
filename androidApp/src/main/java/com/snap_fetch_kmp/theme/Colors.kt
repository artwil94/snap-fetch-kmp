package com.example.snapfetch.ui.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

data class SfColors(
    val primarySurface: Color = Color(0xFF1B1B20),
    val photoDetailsScreen: Color = Color(0xFF2D2E32),
    val primaryTextGray: Color = Color(0xFFAAAAAA),
    val actionButton: Color = Color(0xFFA99AF4),
    val highlightedColor: Color = Color.White,
    val error: Color = Color(0xFFF8326A)
)

val LocalColors = staticCompositionLocalOf { SfColors() }