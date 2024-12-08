package com.snap_fetch_kmp.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import com.example.snap_fetch_kmp.android.R
import com.example.snapfetch.ui.theme.LocalColors
import com.example.snapfetch.ui.theme.LocalDimensions
import com.example.snapfetch.ui.theme.LocalShapes
import com.example.snapfetch.ui.theme.SfColors
import com.example.snapfetch.ui.theme.SfDimensions
import com.example.snapfetch.ui.theme.SfShapes

@Composable
fun SnapFetchTheme(
    darkMode: Boolean = isSystemInDarkTheme(),
    typography: SfTypography = SfTheme.typography,
    dimension: SfDimensions = SfTheme.dimensions,
    shape: SfShapes = SfTheme.shapes,
    colors: SfColors = SfTheme.colors,
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalColors provides colors,
        LocalTypography provides typography,
        LocalDimensions provides dimension,
        LocalShapes provides shape,
    ) {
        content()
    }
}

object SfTheme {
    val typography: SfTypography
        @Composable get() = SfTypography()
    val fonts: Fonts = Fonts()
    val dimensions: SfDimensions
        @Composable get() = SfDimensions()
    val colors: SfColors
        @Composable get() = SfColors()
    val shapes: SfShapes
        @Composable get() = SfShapes()
}

data class Fonts(
    val rubikMedium: FontFamily = FontFamily(Font(R.font.rubik_medium)),
    val robotoBold: FontFamily = FontFamily(Font(R.font.roboto_bold)),
    val robotoMedium: FontFamily = FontFamily(Font(R.font.roboto_medium)),
    val robotoLight: FontFamily = FontFamily(Font(R.font.roboto_light)),
)