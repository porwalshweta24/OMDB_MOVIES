package com.maveric.imdb.storage

import com.maveric.imdb.api.OmdbClient
import com.maveric.imdb.di.get
import com.maveric.imdb.utils.helper.TimedCache
import com.maveric.imdb.utils.helper.TimedCache.Companion.cacheKey
import retrofit2.http.GET
import java.util.concurrent.TimeUnit

class OmdbRepository {
    suspend fun search(
        query: String,
        page: Int = 1
    ) = get<TimedCache>().run {
        val key = cacheKey("${OMDB_PREFIX}search", query, page)
        getOrPut(key, suspend {
            get<OmdbClient>().search(query, page)
        }, TimeUnit.MINUTES.toMillis(3))
    }

    suspend fun details(
        imdbId: String
    ) = get<TimedCache>().run {
        val key = cacheKey("${OMDB_PREFIX}details", imdbId)
        getOrPut(key, suspend {
            get<OmdbClient>().details(imdbId)
        }, TimeUnit.MINUTES.toMillis(2))
    }

    companion object {
        private const val OMDB_PREFIX = "omdb"
    }
}