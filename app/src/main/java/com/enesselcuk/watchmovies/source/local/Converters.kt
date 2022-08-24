package com.enesselcuk.watchmovies.source.local

import androidx.room.TypeConverter
import com.enesselcuk.watchmovies.source.remote.response.categorys.ResultMovies
import com.enesselcuk.watchmovies.source.remote.response.detail.Genre
import com.google.gson.Gson


class Converters {
    @TypeConverter
    fun listToJson(value: List<Int>?) = Gson().toJson(value)


    @TypeConverter
    fun jsonToList(value: String) =
        Gson().fromJson(value, Array<Int>::class.java).toList()

    @TypeConverter
    fun resultToJson(value: List<ResultMovies>?) = Gson().toJson(value)

    @TypeConverter
    fun jsonToResult(value: String) =
        Gson().fromJson(value, Array<ResultMovies>::class.java).toList()

    @TypeConverter
    fun genreToJson(value: List<Genre>?) = Gson().toJson(value)

    @TypeConverter
    fun jsonToGenre(value: String) = Gson().fromJson(value, Array<Genre>::class.java).toList()
}