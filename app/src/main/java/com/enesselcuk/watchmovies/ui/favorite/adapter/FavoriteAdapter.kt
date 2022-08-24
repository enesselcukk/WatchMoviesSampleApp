package com.enesselcuk.watchmovies.ui.favorite.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import com.enesselcuk.watchmovies.R
import com.enesselcuk.watchmovies.source.local.entity.MoviesFavoriteEntity

class FavoriteAdapter(private val itemClick: (MoviesFavoriteEntity) -> Unit) :
    ListAdapter<MoviesFavoriteEntity, FavoriteViewHolder>(FavoriteDiffUtil) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        return FavoriteViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_favorite,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        val favoritePositions = getItem(position)
        holder.hold(favoritePositions, itemClick)
    }

}