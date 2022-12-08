package com.arash.arch.domain.usecase

import arrow.core.Either
import com.arash.arch.domain.base.Error
import com.arash.arch.domain.model.Anime
import com.arash.arch.domain.model.ResponseWrapper
import com.arash.arch.domain.repository.AnimeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAnimeListUseCase @Inject constructor(
    private val repository: AnimeRepository
) {
    operator fun invoke(
        limit: Int,
        offset: Int,
        force: Boolean
    ): Flow<Either<Error, ResponseWrapper<List<Anime>>>> {
        return repository.fetchAnimeList(limit, offset, force)
    }
}