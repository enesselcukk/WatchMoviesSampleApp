package com.enesselcuk.watchmovies.ui.viewPagerFragment


import com.enesselcuk.watchmovies.base.BaseFragment
import com.enesselcuk.watchmovies.databinding.FragmentViewPagerBinding
import com.enesselcuk.watchmovies.source.remote.response.categorys.ResultMovies
import com.enesselcuk.watchmovies.util.CategoryName.ARG_MOVIES
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ViewPagerFragment(private val onClick: (ResultMovies?) -> Unit) :
    BaseFragment<FragmentViewPagerBinding>(FragmentViewPagerBinding::inflate) {

    override fun definition() {

        val arguments: ResultMovies? = arguments?.getParcelable(ARG_MOVIES)
        binding.setDataPager = arguments

        binding.root.setOnClickListener {
            onClick(arguments)
        }
    }
}