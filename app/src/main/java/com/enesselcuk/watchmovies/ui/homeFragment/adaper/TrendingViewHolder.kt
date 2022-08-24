package com.enesselcuk.watchmovies.ui.homeFragment.adaper

import androidx.recyclerview.widget.RecyclerView
import com.enesselcuk.watchmovies.databinding.ItemMoviesTrendingBinding
import com.enesselcuk.watchmovies.source.remote.response.categorys.ResultMovies


class TrendingViewHolder(private val binding: ItemMoviesTrendingBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun trendingHold(movies: ResultMovies?, itemClick: (ResultMovies?) -> Unit) {
        binding.setData = movies
        binding.root.setOnClickListener {
            itemClick(movies)
        }
    }

}