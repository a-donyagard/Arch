package com.arash.arch.data.source

import com.arash.arch.data.model.anime.AnimeListDto
import com.arash.arch.data.model.anime.toAnimeListWrapper
import com.arash.arch.data.source.db.AnimeDao
import com.arash.arch.data.source.db.toAnimeEntityList
import com.arash.arch.data.source.preference.AppPreferencesHelper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(
    private val animeDao: AnimeDao,
    private val preferencesHelper: AppPreferencesHelper
) {
    fun getAnimeList(): Flow<AnimeListDto> {
        return animeDao.getAnimeList().map {
            it.toAnimeListWrapper(preferencesHelper.getPaginationLinks())
        }
    }

    suspend fun insertAnimeList(animeListDto: AnimeListDto) {
        animeDao.insertAnimeList(animeListDto.toAnimeEntityList())
        animeDao.removeExtraRows()
        animeListDto.links?.let { preferencesHelper.setPaginationLinks(it) }
    }

    suspend fun clearAnimeEntity() {
        animeDao.clearAnimeEntity()
    }
}