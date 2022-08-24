package com.enesselcuk.watchmovies.source.repos

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.enesselcuk.watchmovies.domain.MoviesRepos
import com.enesselcuk.watchmovies.source.local.MoviesDatabase
import com.enesselcuk.watchmovies.source.local.entity.MoviesFavoriteEntity
import com.enesselcuk.watchmovies.source.paging.MoviesPagingSource
import com.enesselcuk.watchmovies.source.paging.PagingRemoteMediator
import com.enesselcuk.watchmovies.source.remote.apiService.MoviesService
import com.enesselcuk.watchmovies.source.remote.response.categorys.MoviesResponse
import com.enesselcuk.watchmovies.source.remote.response.categorys.ResultMovies
import com.enesselcuk.watchmovies.source.remote.response.detail.DetailResponse
import com.enesselcuk.watchmovies.source.remote.response.videos.VideoResponse
import com.enesselcuk.watchmovies.util.NetworkResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject


class MoviesRepositoryImpl @Inject constructor(
    private val api: MoviesService,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val database: MoviesDatabase
) : MoviesRepos {

    override suspend fun getIdFavorite(id: Int): Flow<NetworkResult<MoviesFavoriteEntity>> = flow {
        emit(NetworkResult.Loading())
        try {
            val getFavoriteId = database.favoriteDao().getIdFavorite(id)
            emit(NetworkResult.Success(getFavoriteId))
        } catch (ex: Exception) {
            emit(NetworkResult.Error(ex.message))
        } catch (ex: HttpException) {
            emit(NetworkResult.Error(ex.message))
        }
    }

    override suspend fun deleteFavorite(id: Int): Int {
        return database.favoriteDao().deleteID(id)
    }


    override suspend fun allSearch(
        language: String,
        name: String
    ): Flow<PagingData<ResultMovies>> {
        val pageSearch = { MoviesPagingSource(api = api, language = language, categoryName = name) }
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE
            ),
            pagingSourceFactory = pageSearch
        ).flow
    }


    override suspend fun favorite(moviesFavoriteEntity: MoviesFavoriteEntity) {
        database.favoriteDao().insertMovies(moviesFavoriteEntity)
    }

    override suspend fun getFavorite(): Flow<NetworkResult<List<MoviesFavoriteEntity>>> =
        flow {
            emit(NetworkResult.Loading())
            try {
                val daoFavorite = database.favoriteDao().allMovies()
                emit(NetworkResult.Success(daoFavorite))
            } catch (ex: Exception) {
                emit(NetworkResult.Error(ex.message))
            } catch (ex: HttpException) {
                emit(NetworkResult.Error(ex.message))
            }
        }.flowOn(dispatcher)


    override fun getFavorite2(): Flow<MoviesFavoriteEntity> = flow<MoviesFavoriteEntity>
    {
        database.favoriteDao().allMovies().map {
            emit(it)
        }
    }.flowOn(dispatcher)


    override fun getMoviesPaging(
        category: String,
        language: String,
    ): Flow<PagingData<ResultMovies>> {
        val daoFactory = { database.moviesDao().allMovies() }

        @OptIn(ExperimentalPagingApi::class)
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            remoteMediator = PagingRemoteMediator(api, database, category, language),
            pagingSourceFactory = daoFactory,
        ).flow
    }

    override fun getTrending(page: Int): Flow<NetworkResult<MoviesResponse>> = flow {
        emit(NetworkResult.Loading())
        try {
            val trending = api.getTrending(page = page)
            emit(NetworkResult.Success(trending))
        } catch (ex: Exception) {
            emit(NetworkResult.Error(ex.message.toString()))
        } catch (ex: IOException) {
            emit(NetworkResult.Error(ex.message.toString()))
        }
    }.flowOn(dispatcher)

    override fun pagerShuffle(page: Int): Flow<NetworkResult<MoviesResponse>> = flow {
        emit(NetworkResult.Loading())
        try {
            val trending = api.getTrending(page = page)
            emit(NetworkResult.Success(trending))
        } catch (ex: Exception) {
            emit(NetworkResult.Error(ex.message.toString()))
        } catch (ex: IOException) {
            emit(NetworkResult.Error(ex.message.toString()))
        }
    }.flowOn(dispatcher)

    override fun getVideos(videoId: Int, language: String): Flow<NetworkResult<VideoResponse>> =
        flow {
            emit(NetworkResult.Loading())
            try {
                val videosApi = api.getVideos(movie_id = videoId, language = language)
                emit(NetworkResult.Success(videosApi))
            } catch (ex: Exception) {
                emit(NetworkResult.Error(ex.message))
            } catch (ex: HttpException) {
                emit(NetworkResult.Error(ex.message))
            }
        }.flowOn(dispatcher)

    override fun getDetail(movies_id: Int, language: String): Flow<NetworkResult<DetailResponse>> =
        flow {
            emit(NetworkResult.Loading())
            try {
                val videosApi = api.getDetail(movie_id = movies_id, language = language)
                emit(NetworkResult.Success(videosApi))
            } catch (ex: Exception) {
                emit(NetworkResult.Error(ex.message))
            } catch (ex: HttpException) {
                emit(NetworkResult.Error(ex.message))
            }
        }.flowOn(dispatcher)


    companion object {
        const val NETWORK_PAGE_SIZE = 1
    }

}