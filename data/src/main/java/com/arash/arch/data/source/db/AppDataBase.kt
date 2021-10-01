package com.arash.arch.data.source.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [AnimeEntity::class], version = AppDataBase.VERSION, exportSchema = false)
abstract class AppDataBase : RoomDatabase() {
    companion object {
        const val DB_NAME = "arch.db"
        const val VERSION = 2
    }

    abstract fun animeDao(): AnimeDao
}
