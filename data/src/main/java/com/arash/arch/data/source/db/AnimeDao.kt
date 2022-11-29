package com.arash.arch.data.source.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface AnimeDao {
    @Query("SELECT * FROM AnimeEntity")
    fun getAnimeList(): Flow<List<AnimeEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAnimeList(animeList: List<AnimeEntity>)

    @Query("DELETE FROM AnimeEntity WHERE id NOT IN (SELECT id FROM AnimeEntity ORDER BY row_created_time DESC LIMIT 50)")
    suspend fun removeExtraRows()

    @Query("DELETE FROM AnimeEntity")
    suspend fun clearAnimeEntity()
}