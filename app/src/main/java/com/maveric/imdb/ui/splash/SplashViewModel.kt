package com.maveric.imdb.ui.splash

import android.content.SharedPreferences
import android.content.res.Resources
import com.maveric.imdb.api.NewsApi
import com.maveric.imdb.api.NewsClient
import com.maveric.imdb.api.OmdbApi
import com.maveric.imdb.api.OmdbClient
import com.maveric.imdb.di.GlideApp
import com.maveric.imdb.di.get
import com.maveric.imdb.storage.OmdbRepository
import com.maveric.imdb.ui.base.ScopeViewModel
import com.maveric.imdb.utils.field.EventField
import com.maveric.imdb.utils.helper.TimedCache
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.system.measureTimeMillis

class SplashViewModel : ScopeViewModel() {
    val eventInitialized = EventField<Unit>()

    fun initializeAll() = scope.launch {
        val time = measureTimeMillis {
            withContext(IO) {
                // Initialize lazy tasks.
                initializeDi()
                initializeGlide()
            }
        }

        delay(MIN_DELAY_MILLISEC - time)

        eventInitialized.triggerEvent(Unit)
    }

    private fun initializeDi() {
        get<Resources>()
        get<SharedPreferences>()

        get<OmdbApi>()
        get<OmdbClient>()

        get<NewsApi>()
        get<NewsClient>()

        get<TimedCache>()
        get<OmdbRepository>()
    }

    private fun initializeGlide() {
        GlideApp.get(get())
    }

    companion object {
        private const val MIN_DELAY_MILLISEC = 1_000
    }
}