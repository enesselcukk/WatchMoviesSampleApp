package com.enesselcuk.watchmovies.common

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import com.enesselcuk.watchmovies.R

class UnSplashLoadAdapter(private val retry: () -> Unit) : LoadStateAdapter<UnSplashViewHolder>() {
    override fun onBindViewHolder(holder: UnSplashViewHolder, loadState: LoadState) {

        holder.bind(loadState, retry)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): UnSplashViewHolder {
        return UnSplashViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.unsplashload,
                parent,
                false
            )
        )
    }
}