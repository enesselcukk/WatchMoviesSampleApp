package com.enesselcuk.watchmovies.ui.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.enesselcuk.watchmovies.domain.MoviesRepos
import com.enesselcuk.watchmovies.source.local.entity.MoviesFavoriteEntity
import com.enesselcuk.watchmovies.util.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(private val moviesRepos: MoviesRepos) : ViewModel() {

    private val _favoriteEntity: MutableStateFlow<FavoriteUiState> = MutableStateFlow(
        FavoriteUiState(isLoading = false)
    )
    val favoriteEntity: StateFlow<FavoriteUiState> = _favoriteEntity

//    private val _favoriteEntityDelete: MutableStateFlow<FavoriteUiState> = MutableStateFlow(
//        FavoriteUiState(isLoading = false)
//    )
//    val favoriteEntityDelete: StateFlow<FavoriteUiState> = _favoriteEntityDelete

    fun getAllFavorite() {
        viewModelScope.launch {
            moviesRepos.getFavorite().collectLatest {
                uiState(it)
            }
        }
    }

    fun deleteFavorite(id: Int) {
        viewModelScope.launch {
            moviesRepos.deleteFavorite(id)
        }
    }

    private fun uiState(networkResult: NetworkResult<List<MoviesFavoriteEntity>>) {
        when (networkResult) {
            is NetworkResult.Success -> {
                _favoriteEntity.update {
                    it.copy(favoriteEntity = networkResult.data, isLoading = false)
                }
            }
            is NetworkResult.Error -> {
                _favoriteEntity.update {
                    it.copy(isLoading = false, isError = networkResult.message)
                }
            }
            is NetworkResult.Loading -> {
                _favoriteEntity.update {
                    it.copy(isLoading = true)
                }
            }
        }
    }
}