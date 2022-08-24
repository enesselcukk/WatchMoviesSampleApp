package com.enesselcuk.watchmovies.ui.homeFragment.adaper

import androidx.recyclerview.widget.DiffUtil
import com.enesselcuk.watchmovies.source.remote.response.categorys.ResultMovies

object HomeDiffUtil : DiffUtil.ItemCallback<ResultMovies>() {
    override fun areItemsTheSame(
        oldItem: ResultMovies,
        newItem: ResultMovies
    ): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(
        oldItem: ResultMovies,
        newItem: ResultMovies
    ): Boolean =
        oldItem == newItem
}