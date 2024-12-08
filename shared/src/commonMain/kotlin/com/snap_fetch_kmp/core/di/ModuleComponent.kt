package com.snap_fetch_kmp.core.di

import com.snap_fetch_kmp.data.remote.ApiService
import com.snap_fetch_kmp.data.remote.ApiServiceProvider
import com.snap_fetch_kmp.domain.interactor.PhotoRepository
import com.snap_fetch_kmp.domain.interactor.PhotoRepositoryImpl
import org.koin.dsl.module

val sharedModule = module {
    single<ApiService> {
        ApiServiceProvider()
    }

    single<PhotoRepository> {
        PhotoRepositoryImpl(get())
    }
}
