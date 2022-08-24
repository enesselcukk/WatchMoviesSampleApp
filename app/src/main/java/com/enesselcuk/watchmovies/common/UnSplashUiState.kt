package com.enesselcuk.watchmovies.common

import android.content.Context
import androidx.paging.LoadState
import com.enesselcuk.watchmovies.R
import com.enesselcuk.watchmovies.base.BaseUiState


data class UnSplashUiState(private val loadState: LoadState) : BaseUiState() {

    fun getLoadingVisibility() = getViewVisibility(isVisible = loadState is LoadState.Loading)

    fun getErrorVisibility() = getViewVisibility(isVisible = loadState is LoadState.Error)

    fun getErrorMessage(context: Context) = if (loadState is LoadState.Error) {
        loadState.error.localizedMessage ?: context.getString(R.string.error)
    } else ""
}
