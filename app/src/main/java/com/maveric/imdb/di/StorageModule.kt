package com.maveric.imdb.di

import com.maveric.imdb.BuildConfig.*
import com.maveric.imdb.config.CacheConfig
import com.maveric.imdb.storage.OmdbRepository
import com.maveric.imdb.utils.helper.TimedCache
import com.jakewharton.disklrucache.DiskLruCache
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import java.io.File

val storageModule = module {
    single { CacheConfig() }

    single {
        val config = get<CacheConfig>()

        val cacheDir = File(androidContext().cacheDir, "timed")

        val cache = DiskLruCache.open(
            cacheDir, VERSION_CODE,
            2, config.commonCacheSizeBytes
        )

        TimedCache(cache)
    }

    single { OmdbRepository() }
}