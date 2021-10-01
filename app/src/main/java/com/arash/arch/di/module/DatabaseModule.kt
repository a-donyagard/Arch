package com.arash.arch.di.module

import android.content.Context
import androidx.room.Room
import com.arash.arch.data.source.db.AnimeDao
import com.arash.arch.data.source.db.AppDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Provides
    @Singleton
    fun provideRoomDatabase(@ApplicationContext appContext: Context): AppDataBase {
        return Room
            .databaseBuilder(appContext, AppDataBase::class.java, AppDataBase.DB_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideAnimeDao(appDataBase: AppDataBase): AnimeDao {
        return appDataBase.animeDao()
    }
}
