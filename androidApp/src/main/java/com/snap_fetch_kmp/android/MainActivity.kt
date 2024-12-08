package com.snap_fetch_kmp.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.snap_fetch_kmp.navigation.MainScreen
import com.snap_fetch_kmp.theme.SnapFetchTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SnapFetchTheme {
                MainScreen()
            }
        }
    }
}
