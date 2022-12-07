package com.arash.arch.data.source

import com.arash.arch.data.model.ResponseWrapperDto
import com.arash.arch.data.model.anime.AnimeDto
import com.arash.arch.data.model.anime.toResponseWrapper
import com.arash.arch.data.source.db.AnimeDao
import com.arash.arch.data.source.db.toAnimeEntityList
import com.arash.arch.data.source.preference.AppPreferencesHelper
import com.arash.arch.domain.model.Anime
import com.arash.arch.domain.model.ResponseWrapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(
    private val animeDao: AnimeDao,
    private val preferencesHelper: AppPreferencesHelper
) {
    fun getAnimeList(): Flow<ResponseWrapper<List<Anime>>> {
        return animeDao.getAnimeList().map {
            it.toResponseWrapper(preferencesHelper.getPaginationLinks())
        }
    }

    suspend fun insertAnimeList(animeListDto: ResponseWrapperDto<List<AnimeDto>>) {
        animeDao.insertAnimeList(animeListDto.toAnimeEntityList())
        animeDao.removeExtraRows()
        animeListDto.links?.let { preferencesHelper.setPaginationLinks(it) }
    }

    suspend fun clearAnimeEntity() {
        animeDao.clearAnimeEntity()
    }
}