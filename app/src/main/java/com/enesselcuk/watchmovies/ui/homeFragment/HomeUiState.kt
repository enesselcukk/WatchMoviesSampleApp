package com.enesselcuk.watchmovies.ui.homeFragment

import android.content.Context
import androidx.paging.LoadState
import com.enesselcuk.watchmovies.R
import com.enesselcuk.watchmovies.base.BaseUiState
import com.enesselcuk.watchmovies.source.remote.response.categorys.MoviesResponse
import com.enesselcuk.watchmovies.source.remote.response.videos.VideoResponse

//data class HomeUiState(
//    val loadState: LoadState
//) : BaseUiState() {
//    fun getProgressBarVisibility() = getViewVisibility(isVisible = loadState is LoadState.Loading)
//
//    fun getListVisibility() = getViewVisibility(isVisible = loadState is LoadState.NotLoading)
//
//    fun getErrorVisibility() = getViewVisibility(isVisible = loadState is LoadState.Error)
//
//    fun getErrorMessage(context: Context) = if (loadState is LoadState.Error) {
//        loadState.error.localizedMessage ?: context.getString(R.string.error)
//    } else ""
//}

data class HomeUiState(
    val moviesResponse: MoviesResponse? = null,
    val isError: String? = null,
    val isLoading: Boolean? = false
)

