package com.snap_fetch_kmp

import android.app.Application
import com.snap_fetch_kmp.core.di.initKoin
import org.koin.android.ext.koin.androidContext
import timber.log.Timber

class SnapFetchApp : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@SnapFetchApp)
        }
        Timber.plant(Timber.DebugTree())
    }
}