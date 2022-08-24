package com.enesselcuk.watchmovies.ui.category.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagingDataAdapter
import com.enesselcuk.watchmovies.R
import com.enesselcuk.watchmovies.source.remote.response.categorys.ResultMovies
import javax.inject.Inject

class CategoryAdapter (private val moviesClick: (Int?) -> Unit) :
    PagingDataAdapter<ResultMovies, CategoryViewHolder>(CategoryDiffUtil) {
    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val moviesPositions = getItem(position)
        holder.hold(moviesPositions, moviesClick)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder =
        CategoryViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_movies_category,
                parent,
                false
            )
        )
}