package com.midterm.securevpnproxy.di

import com.midterm.securevpnproxy.BuildConfig
import com.midterm.securevpnproxy.util.timber.LineLevelLogging
import com.midterm.securevpnproxy.util.timber.NoLogging
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import timber.log.Timber
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideTimberLogging(): Timber.DebugTree {
        return when {
            BuildConfig.DEBUG -> LineLevelLogging()
            else -> NoLogging()
        }
    }
}