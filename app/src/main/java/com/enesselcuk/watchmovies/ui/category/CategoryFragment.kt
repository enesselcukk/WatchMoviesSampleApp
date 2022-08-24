package com.enesselcuk.watchmovies.ui.category


import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.recyclerview.widget.GridLayoutManager
import com.enesselcuk.watchmovies.base.BaseFragment
import com.enesselcuk.watchmovies.common.UnSplashLoadAdapter
import com.enesselcuk.watchmovies.databinding.FragmentCategoryBinding
import com.enesselcuk.watchmovies.source.remote.response.categorys.ResultMovies
import com.enesselcuk.watchmovies.ui.category.adapter.CategoryAdapter
import com.enesselcuk.watchmovies.util.CategoryName.LANGUAGE
import com.enesselcuk.watchmovies.util.collect
import com.enesselcuk.watchmovies.util.collectLatest
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch


@AndroidEntryPoint
class CategoryFragment : BaseFragment<FragmentCategoryBinding>(FragmentCategoryBinding::inflate) {
    private val categoryViewModel: CategoryViewModel by activityViewModels()
    private val getArgs: CategoryFragmentArgs by navArgs()
    private lateinit var pagingAdapter: CategoryAdapter

    override fun definition() {
        pagingAdapter = CategoryAdapter(::detailClick)
        categoryViewModel.moviesCategory(getArgs.nowplaying, LANGUAGE)

        with(binding) {
            recyclerview.layoutManager =
                GridLayoutManager(
                    requireContext(),
                    3
                )
            recyclerview.adapter =
                pagingAdapter.withLoadStateFooter(UnSplashLoadAdapter(pagingAdapter::retry))
            recyclerview.setHasFixedSize(true)
        }
        pagingRefresh()
    }

    override fun setObserver() {
        collectLatest(categoryViewModel.categoryFlow, ::setUiState)
    }

    private fun setUiState(resultMovies: PagingData<ResultMovies>) {
        lifecycleScope.launch(Dispatchers.Main) {
            pagingAdapter.submitData(resultMovies)
        }
    }

    private fun pagingUiState(loadState: LoadState) {
        binding.setData = CategoryUiState(loadState)
    }

    private fun pagingRefresh() {
        collect(pagingAdapter.loadStateFlow
            .distinctUntilChangedBy { it.source.refresh }
            .map { it.refresh },
            actionsUiState = ::pagingUiState
        )
    }

    private fun detailClick(idToDetail: Int?) {
        idToDetail?.let {
            val action =
                CategoryFragmentDirections.actionCategoryFragmentToDetailFragment(idToDetail)
            findNavController().navigate(action)
        }
    }
}