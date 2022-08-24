package com.enesselcuk.watchmovies.ui.detail

import androidx.lifecycle.*
import com.enesselcuk.watchmovies.domain.MoviesRepos
import com.enesselcuk.watchmovies.source.local.entity.MoviesFavoriteEntity
import com.enesselcuk.watchmovies.source.remote.response.detail.DetailResponse
import com.enesselcuk.watchmovies.source.remote.response.videos.VideoResponse
import com.enesselcuk.watchmovies.util.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repos: MoviesRepos
) : ViewModel() {

    private val _detailFlow: MutableStateFlow<DetailUiState> =
        MutableStateFlow(DetailUiState(isLoading = false))
    val detailFlow: StateFlow<DetailUiState> = _detailFlow

    private val _videosFlow: MutableStateFlow<VideoUiState> =
        MutableStateFlow(VideoUiState(isLoading = false))
    val videosFlow: StateFlow<VideoUiState> = _videosFlow

    private val _favoriteFlow: MutableStateFlow<FavoriteUiState> =
        MutableStateFlow(FavoriteUiState(isLoading = false))
    val favoriteFlow: StateFlow<FavoriteUiState> = _favoriteFlow

    fun getDetail(movies_id: Int, language: String) {
        viewModelScope.launch {
            repos.getDetail(movies_id, language).collectLatest {
                detailUiState(it)
            }
        }
    }

    fun getVideos(movies_id: Int, language: String) {
        viewModelScope.launch {
            repos.getVideos(movies_id, language).collectLatest {
                videoUiState(it)
            }
        }
    }

    fun moviesInsert(moviesFavoriteEntity: MoviesFavoriteEntity) {
        viewModelScope.launch {
            repos.favorite(moviesFavoriteEntity)
        }
    }

    fun getFavoriteId(id: Int) {
        viewModelScope.launch {
            repos.getIdFavorite(id).collectLatest {
                favoriteUiState(it)
            }
        }
    }

    private fun videoUiState(networkResult: NetworkResult<VideoResponse>) {
        when (networkResult) {
            is NetworkResult.Success -> {
                _videosFlow.update { pagerUi ->
                    pagerUi.copy(
                        videosResponse = networkResult.data,
                        isLoading = false
                    )
                }
            }
            is NetworkResult.Error -> {
                _videosFlow.update { pagerUi ->
                    pagerUi.copy(
                        isError = networkResult.message,
                        isLoading = false
                    )
                }
            }
            is NetworkResult.Loading -> {
                _videosFlow.update { pagerUi ->
                    pagerUi.copy(
                        isLoading = true
                    )
                }
            }
        }
    }

    private fun detailUiState(networkResult: NetworkResult<DetailResponse>) {
        when (networkResult) {
            is NetworkResult.Success -> {
                _detailFlow.update { pagerUi ->
                    pagerUi.copy(
                        detailResponse = networkResult.data,
                        isLoading = false
                    )
                }
            }
            is NetworkResult.Error -> {
                _detailFlow.update { pagerUi ->
                    pagerUi.copy(
                        isError = networkResult.message,
                        isLoading = false
                    )
                }
            }
            is NetworkResult.Loading -> {
                _detailFlow.update { pagerUi ->
                    pagerUi.copy(
                        isLoading = true
                    )
                }
            }
        }
    }

    private fun favoriteUiState(networkResult: NetworkResult<MoviesFavoriteEntity>) {
        when (networkResult) {
            is NetworkResult.Success -> {
                _favoriteFlow.update { pagerUi ->
                    pagerUi.copy(
                        favoriteUiState = networkResult.data,
                        isLoading = false
                    )
                }
            }
            is NetworkResult.Error -> {
                _favoriteFlow.update { pagerUi ->
                    pagerUi.copy(
                        isError = networkResult.message,
                        isLoading = false
                    )
                }
            }
            is NetworkResult.Loading -> {
                _favoriteFlow.update { pagerUi ->
                    pagerUi.copy(
                        isLoading = true
                    )
                }
            }
        }
    }
}