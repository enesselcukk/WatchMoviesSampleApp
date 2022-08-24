package com.enesselcuk.watchmovies.ui.category.adapter

import androidx.recyclerview.widget.RecyclerView
import com.enesselcuk.watchmovies.databinding.ItemMoviesCategoryBinding
import com.enesselcuk.watchmovies.source.remote.response.categorys.ResultMovies

class CategoryViewHolder(private val binding: ItemMoviesCategoryBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun hold(movies: ResultMovies?, moviesClick: (Int?) -> Unit) {
        binding.setData = movies
        binding.root.setOnClickListener {
            moviesClick.invoke(movies?.id)
        }
    }

}