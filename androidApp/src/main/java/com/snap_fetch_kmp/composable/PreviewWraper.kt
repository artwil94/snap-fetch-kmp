package com.snap_fetch_kmp.composable

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.navigation.compose.rememberNavController
import com.snap_fetch_kmp.navigation.LocalNavController


@Composable
fun PreviewWrapper(content: @Composable () -> Unit) {
    CompositionLocalProvider(
        LocalNavController provides rememberNavController(),
        content = content
    )
}