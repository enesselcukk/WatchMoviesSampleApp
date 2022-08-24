package com.enesselcuk.watchmovies.ui.favorite

import com.enesselcuk.watchmovies.R
import com.enesselcuk.watchmovies.source.local.entity.MoviesFavoriteEntity

data class FavoriteUiState(
    val favoriteEntity: List<MoviesFavoriteEntity>? = emptyList(),
    val isLoading: Boolean? = null,
    val isError: String? = R.string.error.toString()

)
