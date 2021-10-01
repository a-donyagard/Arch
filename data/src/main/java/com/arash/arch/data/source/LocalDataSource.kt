package com.arash.arch.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.arash.arch.data.model.anime.AnimeListWrapper
import com.arash.arch.data.model.anime.toAnimeListWrapper
import com.arash.arch.data.source.db.AnimeDao
import com.arash.arch.data.source.db.toAnimeEntityList
import com.arash.arch.data.source.preference.AppPreferencesHelper
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(
    private val animeDao: AnimeDao,
    private val preferencesHelper: AppPreferencesHelper
) {
    fun getAnimeList(): LiveData<AnimeListWrapper> {
        return Transformations.map(animeDao.getAnimeList()) {
            it.toAnimeListWrapper(preferencesHelper.getPaginationLinks())
        }
    }

    suspend fun insertAnimeList(animeListWrapper: AnimeListWrapper) {
        animeDao.insertAnimeList(animeListWrapper.toAnimeEntityList())
        animeDao.removeExtraRows()
        animeListWrapper.links?.let { preferencesHelper.setPaginationLinks(it) }
    }

    suspend fun clearAnimeEntity() {
        animeDao.clearAnimeEntity()
    }
}