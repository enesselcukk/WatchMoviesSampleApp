package com.enesselcuk.watchmovies.ui.favorite.adapter

import androidx.recyclerview.widget.DiffUtil
import com.enesselcuk.watchmovies.source.local.entity.MoviesFavoriteEntity

object FavoriteDiffUtil : DiffUtil.ItemCallback<MoviesFavoriteEntity>() {
    override fun areItemsTheSame(
        oldItem: MoviesFavoriteEntity,
        newItem: MoviesFavoriteEntity
    ): Boolean = oldItem.id == newItem.id

    override fun areContentsTheSame(
        oldItem: MoviesFavoriteEntity,
        newItem: MoviesFavoriteEntity
    ): Boolean = oldItem == newItem

}