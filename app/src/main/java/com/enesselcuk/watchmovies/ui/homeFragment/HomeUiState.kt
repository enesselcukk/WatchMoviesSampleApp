package com.enesselcuk.watchmovies.ui.homeFragment


import com.enesselcuk.watchmovies.source.remote.response.categorys.MoviesResponse

data class HomeUiState(
    val moviesResponse: MoviesResponse? = null,
    val isError: String? = null,
    val isLoading: Boolean? = false
)

