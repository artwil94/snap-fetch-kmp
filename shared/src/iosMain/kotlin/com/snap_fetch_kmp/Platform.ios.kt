package com.snap_fetch_kmp

import com.snap_fetch_kmp.presentation.viewmodel.PhotoDetailsViewModel
import com.snap_fetch_kmp.presentation.viewmodel.PhotosListViewModel
import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.dsl.module

actual fun getHttpClient(block: HttpClientConfig<*>.() -> Unit): HttpClient {
    return client(block)
}

private fun client(block: HttpClientConfig<*>.() -> Unit): HttpClient {
    return HttpClient(block)
}

actual fun platformModule() = module {
    factory {
        PhotosListViewModel(get())
    }
    factory {
        PhotoDetailsViewModel(get())
    }
}

object ViewModelProvider : KoinComponent {
    fun getPhotosListViewModel() = get<PhotosListViewModel>()
    fun getPhotoDetailsViewModel() = get<PhotoDetailsViewModel>()
}

