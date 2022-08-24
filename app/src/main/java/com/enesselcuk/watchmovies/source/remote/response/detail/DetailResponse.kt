package com.enesselcuk.watchmovies.source.remote.response.detail

import com.enesselcuk.watchmovies.source.local.entity.MoviesFavoriteEntity


data class DetailResponse(
    val adult: Boolean? = null,
    val backdrop_path: String? = null,
    val belongs_to_collection: BelongsToCollection? = null,
    val budget: Int? = null,
    val genres: List<Genre>? = null,
    val homepage: String? = null,
    val id: Int? = null,
    val imdb_id: String? = null,
    val original_language: String? = null,
    val original_title: String? = null,
    val overview: String? = null,
    val popularity: Double? = null,
    val poster_path: String? = null,
    val production_companies: List<ProductionCompany>? = null,
    val production_countries: List<ProductionCountry>? = null,
    val release_date: String? = null,
    val revenue: Int? = null,
    val runtime: Int? = null,
    val spoken_languages: List<SpokenLanguage>? = null,
    val status: String? = null,
    val tagline: String? = null,
    val title: String? = null,
    val video: Boolean? = null,
    val vote_average: Double? = null,
    val vote_count: Int? = null,

   // var liked: Boolean? = false

) {
    fun mapToFavorite(): MoviesFavoriteEntity {
        return MoviesFavoriteEntity(
            adult = adult,
            backdrop_path = backdrop_path,
            budget = budget,
            genres = genres,
            homepage = homepage,
            id = id,
            imdb_id = imdb_id,
            original_language = original_language,
            original_title = original_title,
            overview = overview,
            popularity = popularity,
            poster_path = poster_path,
            release_date = release_date,
            revenue = revenue,
            runtime = runtime,
            status = status,
            tagline = tagline,
            title = title,
            video = video,
            vote_average = vote_average,
            vote_count = vote_count,
            liked = true
        )
    }
}