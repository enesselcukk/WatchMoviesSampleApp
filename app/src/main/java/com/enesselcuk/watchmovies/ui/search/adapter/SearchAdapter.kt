package com.enesselcuk.watchmovies.ui.search.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagingDataAdapter
import com.enesselcuk.watchmovies.R
import com.enesselcuk.watchmovies.source.remote.response.categorys.ResultMovies


class SearchAdapter(private val searchClick: (Int) -> Unit) :
    PagingDataAdapter<ResultMovies, SearchViewHolder>(SearchDiffUtil) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        return SearchViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_search,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val positionSearch = getItem(position)
        if (positionSearch != null) {
            holder.hold(positionSearch, searchClick)
        }
    }
}