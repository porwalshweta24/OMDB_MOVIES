package com.maveric.imdb

import androidx.multidex.MultiDexApplication
import com.maveric.imdb.BuildConfig.DEBUG
import com.maveric.imdb.di.contextModule
import com.maveric.imdb.di.networkModule
import com.maveric.imdb.di.storageModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.logger.AndroidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.EmptyLogger
import timber.log.Timber

class Application : MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()

        // Initialize Koin DI modules.

        startKoin {
            androidContext(this@Application)
            modules(listOf(contextModule, networkModule, storageModule))
            logger(if (DEBUG) AndroidLogger() else EmptyLogger())
        }

        // Initialize logger.

        if (DEBUG) Timber.plant(Timber.DebugTree())
    }
}