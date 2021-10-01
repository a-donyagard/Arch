package com.arash.arch.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.arash.arch.data.model.anime.AnimeListWrapper
import com.arash.arch.data.model.anime.toKitsoResponse
import com.arash.arch.data.source.db.AnimeDao
import com.arash.arch.data.source.db.toAnimeEntityList
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(
    private val animeDao: AnimeDao
) {
    fun getAnimeList(): LiveData<AnimeListWrapper> {
        return Transformations.map(animeDao.getAnimeList()) {
            it.toKitsoResponse()
        }
    }

    suspend fun insertAnimeList(animeListWrapper: AnimeListWrapper) {
        animeDao.insertAnimeList(animeListWrapper.toAnimeEntityList())
        animeDao.removeExtraRows()
    }

    suspend fun clearAnimeEntity() {
        animeDao.clearAnimeEntity()
    }
}