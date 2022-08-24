package com.enesselcuk.watchmovies.source.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.enesselcuk.watchmovies.source.local.dao.FavoriteDao
import com.enesselcuk.watchmovies.source.local.dao.MoviesDao
import com.enesselcuk.watchmovies.source.local.entity.MoviesFavoriteEntity
import com.enesselcuk.watchmovies.source.local.paging.RemoteKeysDao
import com.enesselcuk.watchmovies.source.local.paging.RemoteKeysEntity
import com.enesselcuk.watchmovies.source.remote.response.categorys.ResultMovies


@Database(
    entities = [
        RemoteKeysEntity::class,
        ResultMovies::class,
        MoviesFavoriteEntity::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class MoviesDatabase : RoomDatabase() {

    abstract fun remoteKeysDao(): RemoteKeysDao
    abstract fun moviesDao(): MoviesDao
    abstract fun favoriteDao(): FavoriteDao

    companion object {
        @Volatile
        private var INSTANCE: MoviesDatabase? = null

        fun getDatabase(context: Context): MoviesDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MoviesDatabase::class.java, "movies_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}