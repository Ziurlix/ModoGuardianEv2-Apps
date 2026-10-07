package com.example.modoguardian.ui.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration

enum class AppWindowSizeClass {
    Compact,
    Medium,
    Expanded
}

@Composable
fun calculateWindowSizeClass(): AppWindowSizeClass {
    val widthDp = LocalConfiguration.current.screenWidthDp

    return when {
        widthDp < 600 -> AppWindowSizeClass.Compact
        widthDp < 840 -> AppWindowSizeClass.Medium
        else -> AppWindowSizeClass.Expanded
    }
}