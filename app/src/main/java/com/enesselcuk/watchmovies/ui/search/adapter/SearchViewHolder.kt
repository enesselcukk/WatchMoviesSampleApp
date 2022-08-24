package com.enesselcuk.watchmovies.ui.search.adapter

import androidx.recyclerview.widget.RecyclerView
import com.enesselcuk.watchmovies.databinding.ItemSearchBinding
import com.enesselcuk.watchmovies.source.remote.response.categorys.ResultMovies


class SearchViewHolder(private val binding: ItemSearchBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun hold(movies: ResultMovies, searchClick: (Int) -> Unit) {
        with(binding) {
            setData = movies
        }

        binding.root.setOnClickListener {
            movies.id?.let { id ->
                searchClick.invoke(id)
            }
        }
    }
}