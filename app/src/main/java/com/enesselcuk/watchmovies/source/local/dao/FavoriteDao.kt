package com.enesselcuk.watchmovies.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.enesselcuk.watchmovies.source.local.entity.MoviesFavoriteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(moviesEntity: MoviesFavoriteEntity)

    @Query("DELETE FROM favorite_entity WHERE id= :idMovies")
    suspend fun deleteID(idMovies: Int): Int

    @Query("SELECT * FROM favorite_entity")
    suspend fun allMovies(): List<MoviesFavoriteEntity>

    @Query("SELECT * FROM favorite_entity WHERE id =:id")
    suspend fun getIdFavorite(id: Int): MoviesFavoriteEntity

}