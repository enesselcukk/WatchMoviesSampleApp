package com.enesselcuk.watchmovies.ui.favorite.adapter

import androidx.recyclerview.widget.RecyclerView
import com.enesselcuk.watchmovies.databinding.ItemFavoriteBinding
import com.enesselcuk.watchmovies.source.local.entity.MoviesFavoriteEntity

class FavoriteViewHolder(private val binding: ItemFavoriteBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun hold(
        moviesFavoriteEntity: MoviesFavoriteEntity,
        itemClick: (MoviesFavoriteEntity) -> Unit
    ) {
        binding.setData = moviesFavoriteEntity

        binding.root.setOnClickListener {
            itemClick.invoke(moviesFavoriteEntity)
        }

    }
}