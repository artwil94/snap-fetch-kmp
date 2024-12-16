package com.snap_fetch_kmp

import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import org.koin.core.module.Module

expect fun platformModule(): Module

expect fun getHttpClient(block: HttpClientConfig<*>.() -> Unit): HttpClient