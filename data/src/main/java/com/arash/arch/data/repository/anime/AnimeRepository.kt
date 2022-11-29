package com.arash.arch.data.repository.anime

import arrow.core.Either
import com.arash.arch.data.mapper.ErrorMapper
import com.arash.arch.data.model.Error
import com.arash.arch.data.model.anime.AnimeListDto
import com.arash.arch.data.repository.BaseRepository
import com.arash.arch.data.source.LocalDataSource
import com.arash.arch.data.source.remote.AnimeDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AnimeRepository @Inject constructor(
    errorMapper: ErrorMapper,
    private val animeDataSource: AnimeDataSource,
    private val localDataSource: LocalDataSource
) : BaseRepository(errorMapper) {
    val animeList: Flow<AnimeListDto> = localDataSource.getAnimeList()
    fun fetchAnimeList(
        limit: Int,
        offset: Int,
        refresh: Boolean
    ): Flow<Either<Error, AnimeListDto>> {
        return getResult {
            val kitsoResponse = animeDataSource.fetchAnimeList(limit, offset)
            if (refresh) localDataSource.clearAnimeEntity()
            localDataSource.insertAnimeList(kitsoResponse)
            kitsoResponse
        }
    }
}