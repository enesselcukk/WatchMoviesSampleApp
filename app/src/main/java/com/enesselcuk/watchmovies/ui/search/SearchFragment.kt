package com.enesselcuk.watchmovies.ui.search


import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.recyclerview.widget.LinearLayoutManager
import com.enesselcuk.watchmovies.base.BaseFragment
import com.enesselcuk.watchmovies.common.UnSplashLoadAdapter
import com.enesselcuk.watchmovies.databinding.FragmentSearchBinding
import com.enesselcuk.watchmovies.source.remote.response.categorys.ResultMovies
import com.enesselcuk.watchmovies.ui.search.adapter.SearchAdapter
import com.enesselcuk.watchmovies.util.Abstract
import com.enesselcuk.watchmovies.util.CategoryName.LANGUAGE
import com.enesselcuk.watchmovies.util.collect
import com.enesselcuk.watchmovies.util.collectLatest
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding>(FragmentSearchBinding::inflate) {
    private val searchViewModel: SearchViewModel by activityViewModels()
    private lateinit var searchAdapter: SearchAdapter

    override fun definition() {
        searchAdapter = SearchAdapter(::searchClickToDetail)

        pagingUiRefresh()

        with(binding) {
            recyclerviewSearch.layoutManager = LinearLayoutManager(requireContext())
            recyclerviewSearch.adapter =
                searchAdapter.withLoadStateFooter(UnSplashLoadAdapter(searchAdapter::retry))

            recyclerviewSearch.setHasFixedSize(true)
        }
    }

    override fun setObserver() {
        collectLatest(searchViewModel.flowSearch, ::searchUiState)
    }

    private fun searchUiState(searchUiState: PagingData<ResultMovies>) {
        lifecycleScope.launch {
            searchAdapter.submitData(searchUiState)
        }
    }

    private fun pagingUiState(state: LoadState) {
        binding.setDataLoadState = SearchUiState(state)
    }

    private fun pagingUiRefresh() {
        collect(searchAdapter.loadStateFlow
            .distinctUntilChangedBy { it.source.refresh }
            .map { it.refresh },
            actionsUiState = ::pagingUiState
        )
    }

    private fun searchClickToDetail(resultId: Int) {
        val action = SearchFragmentDirections.actionSearchFragmentToDetailFragment(resultId)
        findNavController().navigate(action)

    }

    override fun search() {
        binding.searchBar.setOnQueryTextListener(object : Abstract.SearchAbstract() {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    if (query.isNotEmpty() && query.length >= 2) {
                        searchViewModel.searchAll(LANGUAGE, query)
                    }
                    binding.recyclerviewSearch.scrollToPosition(0)
                }
                return false
            }
        })
    }
}