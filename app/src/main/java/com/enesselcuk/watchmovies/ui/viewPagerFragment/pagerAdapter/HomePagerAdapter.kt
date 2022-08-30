package com.enesselcuk.watchmovies.ui.viewPagerFragment.pagerAdapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.enesselcuk.watchmovies.source.remote.response.categorys.ResultMovies
import com.enesselcuk.watchmovies.ui.viewPagerFragment.ViewPagerFragment
import com.enesselcuk.watchmovies.util.CategoryName.ARG_MOVIES


class HomePagerAdapter(
    fragment: Fragment,
    private val listMovies: List<ResultMovies>,
    private val click: (ResultMovies?) -> Unit
) :
    FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = listMovies.size

    override fun createFragment(position: Int): Fragment {
        val fragment = ViewPagerFragment(click)
        when (position) {
            0 -> fragment.arguments = Bundle().apply {
                putParcelable(ARG_MOVIES, listMovies[0])
            }
            1 -> fragment.arguments = Bundle().apply {
                putParcelable(ARG_MOVIES, listMovies[1])
            }
            2 -> fragment.arguments = Bundle().apply {
                putParcelable(ARG_MOVIES, listMovies[2])
            }
            3 -> fragment.arguments = Bundle().apply {
                putParcelable(ARG_MOVIES, listMovies[3])
            }
            4 -> fragment.arguments = Bundle().apply {
                putParcelable(ARG_MOVIES, listMovies[4])
            }
            5 -> fragment.arguments = Bundle().apply {
                putParcelable(ARG_MOVIES, listMovies[5])
            }
        }
        return fragment
    }
}