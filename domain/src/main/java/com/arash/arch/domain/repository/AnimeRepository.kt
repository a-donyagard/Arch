package com.arash.arch.domain.repository

import arrow.core.Either
import com.arash.arch.domain.base.Error
import com.arash.arch.domain.model.Anime
import com.arash.arch.domain.model.ResponseWrapper
import kotlinx.coroutines.flow.Flow

interface AnimeRepository {
    fun fetchAnimeList(
        limit: Int,
        offset: Int,
        refresh: Boolean
    ): Flow<Either<Error, ResponseWrapper<Anime>>>

    fun getAnimeListFromDB(): Flow<ResponseWrapper<Anime>>
}