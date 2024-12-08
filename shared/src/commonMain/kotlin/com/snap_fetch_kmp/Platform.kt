package com.snap_fetch_kmp

import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform

expect fun getHttpClient(block: HttpClientConfig<*>.() -> Unit): HttpClient