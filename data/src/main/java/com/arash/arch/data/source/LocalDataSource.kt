package com.arash.arch.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.arash.arch.data.model.anime.KitsoResponse
import com.arash.arch.data.model.anime.toKitsoResponse
import com.arash.arch.data.source.db.AnimeDao
import com.arash.arch.data.source.db.toAnimeEntityList
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(
    private val animeDao: AnimeDao
) {
    fun getAnimeList(): LiveData<KitsoResponse> {
        return Transformations.map(animeDao.getAnimeList()) {
            it.toKitsoResponse()
        }
    }

    suspend fun insertAnimeList(kitsoResponse: KitsoResponse) {
        animeDao.insertAnimeList(kitsoResponse.toAnimeEntityList())
    }
}