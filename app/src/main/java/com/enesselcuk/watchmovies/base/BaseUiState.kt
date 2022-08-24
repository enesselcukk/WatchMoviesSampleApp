package com.enesselcuk.watchmovies.base

import android.view.View

open class BaseUiState {
    fun getViewVisibility(isVisible: Boolean?) = if (isVisible == true) View.VISIBLE else View.GONE
}