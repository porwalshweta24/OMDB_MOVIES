package com.maveric.imdb.api

import com.maveric.imdb.api.model.omdb.OmdbSearchData
import com.maveric.imdb.api.model.omdb.OmdbDetailsResult
import com.maveric.imdb.api.model.omdb.OmdbResult
import com.maveric.imdb.api.model.omdb.OmdbSearchResult
import com.maveric.imdb.config.NetworkConfig
import com.maveric.imdb.di.get
import com.maveric.imdb.ui.movies.helper.MovieDetailsItem
import com.maveric.imdb.ui.movies.helper.MovieItem
import retrofit2.http.GET
import retrofit2.http.Query

interface OmdbApi {
    @GET(".")
    suspend fun search(
        @Query("apikey") key: String,
        @Query("s") query: String,
        @Query("page") page: Int
    ): OmdbSearchResult

    @GET(".")
    suspend fun details(
        @Query("apikey") key: String,
        @Query("i") imdbId: String,
        @Query("plot") plot: String
    ): OmdbDetailsResult
}

class OmdbClient {
    suspend fun search(
        query: String,
        page: Int
    ): OmdbSearchData {
        val key = randomKey()
        val result = get<OmdbApi>().search(key, query, page).also {
            checkResult(it)
        }

        val total = result.total?.toIntOrNull() ?: throw RuntimeException("Wrong total.")
        val movies = result.search?.map { MovieItem.fromSubjectOrThrow(it) } ?: listOf()
        return movies to total
    }

    suspend fun details(
        imdbId: String
    ): MovieDetailsItem {
        val key = randomKey()
        val plot = "full"
        val result = get<OmdbApi>().details(key, imdbId, plot).also {
            checkResult(it)
        }

        return MovieDetailsItem.fromResultOrThrow(result)
    }

    companion object {
        fun randomKey() = get<NetworkConfig>().omdbKeys.random()

        fun checkResult(result: OmdbResult) = with(result) {
            if (response != "True") throw RuntimeException(error ?: "Unknown error!")
        }
    }
}