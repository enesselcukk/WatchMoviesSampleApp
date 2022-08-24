package com.enesselcuk.watchmovies.di

import android.content.Context
import androidx.room.Room
import com.enesselcuk.watchmovies.source.local.MoviesDatabase
import com.enesselcuk.watchmovies.source.local.dao.FavoriteDao
import com.enesselcuk.watchmovies.source.local.dao.MoviesDao
import com.enesselcuk.watchmovies.source.local.paging.RemoteKeysDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@[Module InstallIn(SingletonComponent::class)]
object RoomModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): MoviesDatabase {
        return Room.databaseBuilder(context, MoviesDatabase::class.java, "movies_database")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideMoviesDao(
        db: MoviesDatabase,
    ): MoviesDao = db.moviesDao()

    @Provides
    @Singleton
    fun provideRemoteKeys(
        db: MoviesDatabase
    ): RemoteKeysDao = db.remoteKeysDao()

    @Provides
    @Singleton
    fun provideFavorite(
        db: MoviesDatabase
    ): FavoriteDao = db.favoriteDao()

}