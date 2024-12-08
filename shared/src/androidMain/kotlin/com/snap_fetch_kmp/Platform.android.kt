package com.snap_fetch_kmp

import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.okhttp.OkHttp

class AndroidPlatform : Platform {
    override val name: String = "Android ${android.os.Build.VERSION.SDK_INT}"
}

actual fun getPlatform(): Platform = AndroidPlatform()

actual fun getHttpClient(block: HttpClientConfig<*>.() -> Unit): HttpClient {
    return getHttpClientOKHttp(block)
}

private fun getHttpClientOKHttp(block: HttpClientConfig<*>.() -> Unit): HttpClient {
    return HttpClient(OkHttp.create(), block)
}