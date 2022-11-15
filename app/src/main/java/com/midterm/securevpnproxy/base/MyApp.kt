package com.midterm.securevpnproxy.base

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import javax.inject.Inject

@HiltAndroidApp
class MyApp: Application() {
    @Inject
    lateinit var logging: Timber.DebugTree

    override fun onCreate() {
        super.onCreate()
        initTimberLogging()
    }

    private fun initTimberLogging() {
        Timber.plant(logging)
    }
}