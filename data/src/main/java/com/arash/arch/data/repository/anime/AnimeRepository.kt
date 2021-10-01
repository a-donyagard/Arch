package com.arash.arch.data.repository.anime

import androidx.lifecycle.LiveData
import arrow.core.Either
import com.arash.arch.data.mapper.ErrorMapper
import com.arash.arch.data.model.Error
import com.arash.arch.data.model.anime.AnimeListWrapper
import com.arash.arch.data.repository.BaseRepository
import com.arash.arch.data.source.LocalDataSource
import com.arash.arch.data.source.remote.AnimeDataSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AnimeRepository @Inject constructor(
    errorMapper: ErrorMapper,
    private val animeDataSource: AnimeDataSource,
    private val localDataSource: LocalDataSource
) : BaseRepository(errorMapper) {
    val animeList: LiveData<AnimeListWrapper> = localDataSource.getAnimeList()
    suspend fun fetchAnimeList(
        limit: Int,
        offset: Int,
        refresh: Boolean
    ): Either<Error, AnimeListWrapper> {
        return getResult {
            val kitsoResponse = animeDataSource.fetchAnimeList(limit, offset)
            if (refresh) localDataSource.clearAnimeEntity()
            localDataSource.insertAnimeList(kitsoResponse)
            kitsoResponse
        }
    }
}