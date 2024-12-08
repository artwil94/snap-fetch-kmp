package com.snap_fetch_kmp.core.di

import com.snap_fetch_kmp.data.remote.ApiService
import com.snap_fetch_kmp.data.remote.ApiServiceProvider
import com.snap_fetch_kmp.domain.interactor.PhotoRepository
import com.snap_fetch_kmp.domain.interactor.PhotoRepositoryImpl
import com.snap_fetch_kmp.platformModule
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

val sharedModule = module {
    single<ApiService> {
        ApiServiceProvider()
    }

    single<PhotoRepository> {
        PhotoRepositoryImpl(get())
    }
}

@Suppress("UNUSED_PARAMETER")
fun initKoin(appDeclaration: KoinAppDeclaration = {}) = startKoin {
    modules(
        sharedModule,
        platformModule()
    )
}

fun initKoin() = initKoin {}