package com.arash.arch.domain.usecase

import com.arash.arch.domain.model.Anime
import com.arash.arch.domain.model.ResponseWrapper
import com.arash.arch.domain.repository.AnimeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAnimeListFromDbUseCase @Inject constructor(
    private val repository: AnimeRepository
) {
    operator fun invoke(): Flow<ResponseWrapper<List<Anime>>> {
        return repository.getAnimeListFromDB()
    }
}