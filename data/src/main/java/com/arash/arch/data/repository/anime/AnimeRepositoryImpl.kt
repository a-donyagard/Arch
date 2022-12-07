package com.arash.arch.data.repository.anime

import arrow.core.Either
import com.arash.arch.data.mapper.ErrorMapper
import com.arash.arch.data.repository.BaseRepository
import com.arash.arch.data.source.LocalDataSource
import com.arash.arch.data.source.remote.AnimeDataSource
import com.arash.arch.domain.base.Error
import com.arash.arch.domain.model.Anime
import com.arash.arch.domain.model.ResponseWrapper
import com.arash.arch.domain.repository.AnimeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AnimeRepositoryImpl @Inject constructor(
    errorMapper: ErrorMapper,
    private val animeDataSource: AnimeDataSource,
    private val localDataSource: LocalDataSource
) : BaseRepository(errorMapper), AnimeRepository {
    override fun fetchAnimeList(
        limit: Int,
        offset: Int,
        refresh: Boolean
    ): Flow<Either<Error, ResponseWrapper<List<Anime>>>> {
        return getResult {
            val kitsoResponse = animeDataSource.fetchAnimeList(limit, offset)
            if (refresh) localDataSource.clearAnimeEntity()
            localDataSource.insertAnimeList(kitsoResponse)
            kitsoResponse
        }
    }

    override fun getAnimeListFromDB(): Flow<ResponseWrapper<List<Anime>>> {
        return localDataSource.getAnimeList()
    }
}