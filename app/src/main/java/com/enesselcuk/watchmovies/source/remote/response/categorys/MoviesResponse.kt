package com.enesselcuk.watchmovies.source.remote.response.categorys

import com.google.gson.annotations.SerializedName


data class MoviesResponse(
    @field:SerializedName("page") val page: Int,
    @field:SerializedName("results") val results: List<ResultMovies>,
    @field:SerializedName("total_pages") val total_pages: Int,
    @field:SerializedName("total_results") val total_results: Int
)