package com.enesselcuk.watchmovies.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.enesselcuk.watchmovies.source.remote.response.detail.*


@Entity(tableName = "favorite_entity")
data class MoviesFavoriteEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int? = null,
    @ColumnInfo(name = "adult") val adult: Boolean? = null,
    @ColumnInfo(name = "backdrop_path") val backdrop_path: String? = null,
    @ColumnInfo(name = "budget") val budget: Int? = null,
    @ColumnInfo(name = "genres") val genres: List<Genre>? = null,
    @ColumnInfo(name = "homepage") val homepage: String? = null,
    @ColumnInfo(name = "imdb_id") val imdb_id: String? = null,
    @ColumnInfo(name = "original_language") val original_language: String? = null,
    @ColumnInfo(name = "original_title") val original_title: String? = null,
    @ColumnInfo(name = "overview") val overview: String? = null,
    @ColumnInfo(name = "popularity") val popularity: Double? = null,
    @ColumnInfo(name = "poster_path") val poster_path: String? = null,
    @ColumnInfo(name = "release_date") val release_date: String? = null,
    @ColumnInfo(name = "revenue") val revenue: Int? = null,
    @ColumnInfo(name = "runtime") val runtime: Int? = null,
    @ColumnInfo(name = "status") val status: String? = null,
    @ColumnInfo(name = "tagline") val tagline: String? = null,
    @ColumnInfo(name = "title") val title: String? = null,
    @ColumnInfo(name = "video") val video: Boolean? = null,
    @ColumnInfo(name = "vote_average") val vote_average: Double? = null,
    @ColumnInfo(name = "vote_count") val vote_count: Int? = null,

    var liked: Boolean? = false
)

