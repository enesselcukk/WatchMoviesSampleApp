package com.enesselcuk.watchmovies.ui.detail

import com.enesselcuk.watchmovies.R
import com.enesselcuk.watchmovies.source.local.entity.MoviesFavoriteEntity
import com.enesselcuk.watchmovies.source.remote.response.detail.DetailResponse
import com.enesselcuk.watchmovies.source.remote.response.videos.VideoResponse

data class DetailUiState(
    val detailResponse: DetailResponse? = null,
    val isLoading: Boolean? = false,
    val isError: String? = R.string.error.toString()
)

data class VideoUiState(
    val videosResponse: VideoResponse? = null,
    val isLoading: Boolean? = false,
    val isError: String? = R.string.error.toString()
)

data class FavoriteUiState(
    val favoriteUiState: MoviesFavoriteEntity? = null,
    val isLoading: Boolean? = false,
    val isError: String? = R.string.error.toString()
)
