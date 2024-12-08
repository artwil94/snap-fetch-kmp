package com.snap_fetch_kmp

import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import platform.UIKit.UIDevice

class IOSPlatform: Platform {
    override val name: String = UIDevice.currentDevice.systemName() + " " + UIDevice.currentDevice.systemVersion
}

actual fun getPlatform(): Platform = IOSPlatform()

actual fun getHttpClient(block: HttpClientConfig<*>.() -> Unit): HttpClient {
    return client(block)
}

private fun client(block: HttpClientConfig<*>.() -> Unit): HttpClient {
    return HttpClient(block)
}

