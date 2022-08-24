package com.enesselcuk.watchmovies.domain

import androidx.paging.PagingData
import com.enesselcuk.watchmovies.source.local.entity.MoviesFavoriteEntity
import com.enesselcuk.watchmovies.source.remote.response.categorys.MoviesResponse
import com.enesselcuk.watchmovies.source.remote.response.categorys.ResultMovies
import com.enesselcuk.watchmovies.source.remote.response.detail.DetailResponse
import com.enesselcuk.watchmovies.source.remote.response.videos.VideoResponse
import com.enesselcuk.watchmovies.util.NetworkResult
import kotlinx.coroutines.flow.Flow

interface MoviesRepos {
    fun getMoviesPaging(category: String, language: String): Flow<PagingData<ResultMovies>>
    fun getTrending(page: Int): Flow<NetworkResult<MoviesResponse>>
    fun pagerShuffle(page: Int): Flow<NetworkResult<MoviesResponse>>
    fun getVideos(videoId: Int, language: String): Flow<NetworkResult<VideoResponse>>
    fun getDetail(movies_id: Int, language: String): Flow<NetworkResult<DetailResponse>>
    suspend fun favorite(moviesFavoriteEntity: MoviesFavoriteEntity)
    suspend fun getFavorite(): Flow<NetworkResult<List<MoviesFavoriteEntity>>>
    fun getFavorite2(): Flow<MoviesFavoriteEntity>

    suspend fun getIdFavorite(id: Int): Flow<NetworkResult<MoviesFavoriteEntity>>
    suspend fun allSearch(language: String, name: String): Flow<PagingData<ResultMovies>>

    suspend fun deleteFavorite(id: Int): Int


}
