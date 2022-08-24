package com.enesselcuk.watchmovies.ui.viewPagerFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.enesselcuk.watchmovies.R
import com.enesselcuk.watchmovies.databinding.FragmentViewPagerBinding
import com.enesselcuk.watchmovies.source.remote.response.categorys.ResultMovies
import com.enesselcuk.watchmovies.util.CategoryName.ARG_MOVIES
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ViewPagerFragment(private val onClick: (ResultMovies?) -> Unit) : Fragment() {
    private lateinit var binding: FragmentViewPagerBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_view_pager, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val arguments: ResultMovies? = arguments?.getParcelable(ARG_MOVIES)
        binding.setDataPager = arguments

        binding.root.setOnClickListener {
            onClick(arguments)
        }
    }


}