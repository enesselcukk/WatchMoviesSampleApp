package com.enesselcuk.watchmovies.ui.homeFragment


import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.enesselcuk.watchmovies.R
import com.enesselcuk.watchmovies.base.BaseFragment
import com.enesselcuk.watchmovies.databinding.FragmentHomeBinding
import com.enesselcuk.watchmovies.source.remote.response.categorys.ResultMovies
import com.enesselcuk.watchmovies.ui.homeFragment.adaper.HomeAdapter
import com.enesselcuk.watchmovies.ui.viewPagerFragment.DepthPageTransformer
import com.enesselcuk.watchmovies.ui.viewPagerFragment.pagerAdapter.HomePagerAdapter
import com.enesselcuk.watchmovies.util.collectLatest
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {
    private val homeViewModel: HomeViewModel by activityViewModels()
    private lateinit var pagerAdapter: HomePagerAdapter

    private lateinit var homeAdapter: HomeAdapter
    override fun definition() {
        homeAdapter = HomeAdapter(::trendClick)
        binding.mainViewPager.setPageTransformer(DepthPageTransformer())
        dialog()
    }

    override fun setObserver() {
        homeViewModel.getTrendingMovies()
        homeViewModel.getLiving()

        with(binding) {
            recyclerview.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            recyclerview.adapter = homeAdapter


            searchBarImage.setOnClickListener {
                findNavController().navigate(R.id.searchFragment)
            }
        }

        collectLatest(homeViewModel.moviesTrendingState, ::setMovies)
        collectLatest(homeViewModel.allMovies, ::pagerUiState)
    }

    private fun setMovies(homeSetData: HomeUiState) {
        homeSetData.moviesResponse?.results?.let { moviesResult ->
            homeAdapter.submitList(moviesResult.shuffled().take(8))
        }
        binding.setData = homeSetData

    }

    private fun pagerUiState(pagerUiState: HomeUiState) {
        pagerUiState.moviesResponse?.results?.let {
            pagerAdapter =
                HomePagerAdapter(fragment = this, it.shuffled().take(6), ::onMoviesClick)
            binding.recyclerview.setHasFixedSize(true)
            viewPagerAdapter()
        }
    }

    private fun onMoviesClick(result: ResultMovies?) {
        result?.id?.let { id ->
            val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(id)
            findNavController().navigate(action)
        }
    }

    private fun trendClick(result: ResultMovies?) {
        result?.id?.let { id ->
            val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(id)
            findNavController().navigate(action)
        }
    }

    private fun viewPagerAdapter() {
        binding.mainViewPager.adapter = pagerAdapter
        TabLayoutMediator(
            binding.mainTabLayout,
            binding.mainViewPager
        ) { _, _ -> }.attach()
    }

    private fun dialog() {
        binding.imgCategory.setOnClickListener {
            findNavController().navigate(R.id.categoryDialogFragment)
        }
    }
}