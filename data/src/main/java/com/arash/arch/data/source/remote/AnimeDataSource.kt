package com.arash.arch.data.source.remote

import com.arash.arch.data.model.anime.AnimeListDto
import retrofit2.http.GET
import retrofit2.http.Query

interface AnimeDataSource {
    @GET("anime")
    suspend fun fetchAnimeList(
        @Query("page[limit]") limit: Int,
        @Query("page[offset]") offset: Int
    ): AnimeListDto
}