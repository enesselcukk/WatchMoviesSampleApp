package com.enesselcuk.watchmovies.source.remote.response.categorys

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


@Entity(tableName = "movies_entity")
@Parcelize

data class ResultMovies(
    @PrimaryKey @field:SerializedName("id") val id: Int? = null,
    @field:SerializedName("adult") val adult: Boolean? = null,
    @field:SerializedName("backdrop_path") val backdrop_path: String? = null,
    @field:SerializedName("genre_ids") val genre_ids: List<Int>? = null,
    @field:SerializedName("original_language") val original_language: String? = null,
    @field:SerializedName("original_title") val original_title: String? = null,
    @field:SerializedName("overview") val overview: String? = null,
    @field:SerializedName("popularity") val popularity: Double? = null,
    @field:SerializedName("poster_path") val poster_path: String? = null,
    @field:SerializedName("release_date") val release_date: String? = null,
    @field:SerializedName("title") val title: String? = null,
    @field:SerializedName("video") val video: Boolean? = null,
    @field:SerializedName("vote_average") val vote_average: Double? = null,
    @field:SerializedName("vote_count") val vote_count: Int? = null

) : Parcelable

