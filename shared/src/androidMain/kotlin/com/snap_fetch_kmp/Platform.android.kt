package com.snap_fetch_kmp

import com.snap_fetch_kmp.presentation.viewmodel.PhotosListViewModel
import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.okhttp.OkHttp
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

actual fun getHttpClient(block: HttpClientConfig<*>.() -> Unit): HttpClient {
    return getHttpClientOKHttp(block)
}

private fun getHttpClientOKHttp(block: HttpClientConfig<*>.() -> Unit): HttpClient {
    return HttpClient(OkHttp.create(), block)
}

actual fun platformModule() = module {
    viewModel {
        PhotosListViewModel(get())
    }
}