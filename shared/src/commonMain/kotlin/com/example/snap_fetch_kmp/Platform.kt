package com.example.snap_fetch_kmp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform