package com.enesselcuk.watchmovies.source.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.enesselcuk.watchmovies.source.remote.response.categorys.ResultMovies

@Dao
interface MoviesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(moviesEntity: ResultMovies)

    @Query("DELETE FROM movies_entity")
    suspend fun clearRepos()

    @Query("SELECT * FROM movies_entity")
    fun allMovies(): PagingSource<Int, ResultMovies>
//
//    @Query("SELECT * FROM movies_entity WHERE id LIKE :name ")
//    fun searchByName(name: String?): PagingSource<Int, Item>

}