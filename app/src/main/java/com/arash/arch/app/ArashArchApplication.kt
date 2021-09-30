package com.arash.arch.app

import android.app.Application
import com.arash.arch.BuildConfig
import com.facebook.stetho.Stetho
import dagger.hilt.android.HiltAndroidApp

/**
 * Custom [Application] class for app that prepare app for running
 */
@HiltAndroidApp
class ArashArchApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initDebugModeValues()
    }

    private fun initDebugModeValues() {
        if (BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this)
        }
    }
}