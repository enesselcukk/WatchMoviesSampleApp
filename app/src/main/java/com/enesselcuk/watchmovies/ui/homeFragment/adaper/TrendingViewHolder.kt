package com.enesselcuk.watchmovies.ui.homeFragment.adaper

import androidx.recyclerview.widget.RecyclerView
import com.enesselcuk.watchmovies.databinding.ItemMoviesTrendingBinding
import com.enesselcuk.watchmovies.source.remote.response.categorys.ResultMovies
import java.text.SimpleDateFormat
import java.util.*

class TrendingViewHolder(private val binding: ItemMoviesTrendingBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun trendingHold(movies: ResultMovies?, itemClick: (ResultMovies?) -> Unit) {

        binding.setData = movies

        val dateParse = SimpleDateFormat("yyyy-mm-dd", Locale.getDefault())
        val dateFormat = SimpleDateFormat("dd/mm/yyyy", Locale.getDefault())

        if (movies?.title != null) {
            val text = dateFormat.format(dateParse.parse(movies?.release_date))
            binding.textDate.text = text

        } else {
            binding.textDate.text = UNKNOWN
        }

        binding.root.setOnClickListener {
            itemClick(movies)
        }
    }

    companion object {
        const val UNKNOWN = "unknown"
    }
}