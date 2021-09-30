package com.arash.arch.data.repository.anime

import arrow.core.Either
import com.arash.arch.data.mapper.ErrorMapper
import com.arash.arch.data.model.Error
import com.arash.arch.data.model.anime.KitsoResponse
import com.arash.arch.data.repository.BaseRepository
import com.arash.arch.data.source.remote.AnimeDataSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AnimeRepository @Inject constructor(
    errorMapper: ErrorMapper,
    private val animeDataSource: AnimeDataSource
) : BaseRepository(errorMapper) {
    suspend fun getAnimeList(limit: Int, offset: Int): Either<Error, KitsoResponse> {
        return getResult { animeDataSource.getAnimeList(limit, offset) }
    }
}