package com.arash.arch.data.source.db

import androidx.room.RoomDatabase

// todo
// @Database(entities = [], version = AppDataBase.VERSION)
abstract class AppDataBase : RoomDatabase() {
    companion object {
        const val DB_NAME = "arch.db"
    }
}
