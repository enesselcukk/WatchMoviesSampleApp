package com.enesselcuk.watchmovies.ui.homeFragment.adaper

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import com.enesselcuk.watchmovies.R
import com.enesselcuk.watchmovies.source.remote.response.categorys.ResultMovies

class HomeAdapter(private val itemClick: (ResultMovies?) -> Unit) :
    ListAdapter<ResultMovies, TrendingViewHolder>(HomeDiffUtil) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrendingViewHolder =

        TrendingViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_movies_trending,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: TrendingViewHolder, position: Int) {
        val moviesPosition = getItem(position)

        holder.trendingHold(moviesPosition, itemClick)
    }
}