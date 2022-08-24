package com.enesselcuk.watchmovies.ui.favorite


import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.enesselcuk.watchmovies.base.BaseFragment
import com.enesselcuk.watchmovies.databinding.FragmentFavoriteBinding
import com.enesselcuk.watchmovies.source.local.entity.MoviesFavoriteEntity
import com.enesselcuk.watchmovies.ui.favorite.adapter.FavoriteAdapter
import com.enesselcuk.watchmovies.util.Abstract
import com.enesselcuk.watchmovies.util.collectLatest
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class FavoriteFragment : BaseFragment<FragmentFavoriteBinding>(FragmentFavoriteBinding::inflate) {
    private lateinit var favoriteAdapter: FavoriteAdapter
    private val favoriteViewModel: FavoriteViewModel by activityViewModels()

    override fun definition() {
        favoriteAdapter = FavoriteAdapter(::itemClick)
        favoriteViewModel.getAllFavorite()
        with(binding) {
            recyclerview.layoutManager = LinearLayoutManager(requireContext())
            recyclerview.adapter = favoriteAdapter
            recyclerview.setHasFixedSize(true)
        }
    }

    override fun setObserver() {
        collectLatest(favoriteViewModel.favoriteEntity, ::uiState)
    }

    private fun uiState(favoriteUiState: FavoriteUiState) {
        favoriteUiState.favoriteEntity?.let {
            favoriteAdapter.submitList(it)
        }
    }

    private fun itemClick(moviesFavoriteEntity: MoviesFavoriteEntity) {
        moviesFavoriteEntity.id?.let {
            val action =
                FavoriteFragmentDirections.actionFavoriteFragmentToDetailFragment(it)
            findNavController().navigate(action)
        }

    }

    override fun swipe() {
        val swipe = object : Abstract.SwipeAbstract() {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.bindingAdapterPosition
                val noteData = favoriteAdapter.currentList[position]

                noteData?.id.let { it?.let { it1 -> favoriteViewModel.deleteFavorite(it1) } }
                view?.let {
                    Snackbar.make(it, "deleted", Snackbar.LENGTH_SHORT).show()
                }
                favoriteViewModel.getAllFavorite()
            }
        }
        ItemTouchHelper(swipe).apply {
            attachToRecyclerView(binding.recyclerview)
        }
    }
}