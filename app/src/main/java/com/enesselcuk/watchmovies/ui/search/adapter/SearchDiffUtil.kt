package com.enesselcuk.watchmovies.ui.search.adapter

import androidx.recyclerview.widget.DiffUtil
import com.enesselcuk.watchmovies.source.remote.response.categorys.ResultMovies

object SearchDiffUtil : DiffUtil.ItemCallback<ResultMovies>() {
    override fun areItemsTheSame(oldItem: ResultMovies, newItem: ResultMovies): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: ResultMovies, newItem: ResultMovies): Boolean =
        oldItem == newItem
}