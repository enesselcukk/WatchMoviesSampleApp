package com.enesselcuk.watchmovies.source.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.enesselcuk.watchmovies.source.local.MoviesDatabase
import com.enesselcuk.watchmovies.source.local.paging.RemoteKeysEntity
import com.enesselcuk.watchmovies.source.remote.apiService.MoviesService
import com.enesselcuk.watchmovies.source.remote.response.categorys.ResultMovies
import retrofit2.HttpException
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class PagingRemoteMediator(
    private val api: MoviesService,
    private val database: MoviesDatabase,
    private val categoryName: String,
    private val language: String,
//    private val authenticationToken: String?
) : RemoteMediator<Int, ResultMovies>() {
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, ResultMovies>
    ): MediatorResult {
        val page = when (loadType) {
            LoadType.REFRESH -> {
                val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                remoteKeys?.nextKey?.minus(1) ?: MOVIES_STARTING_PAGE_INDEX
            }
            LoadType.APPEND -> {
                val remoteKeys = getRemoteKeyForLastItem(state)
                val nextKey = remoteKeys?.nextKey
                nextKey
                    ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
                nextKey
            }
            LoadType.PREPEND -> {
                val remoteKeys = getRemoteKeyForFirstItem(state)
                val prevKey = remoteKeys?.prevKey
                    ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
                prevKey
            }
        }

        try {
            val apiResponse =
                api.getMovies(
                    movies = categoryName,
                    language = language,
                    page = page,
//                    bearer = authenticationToken
                )
            val repos = apiResponse.results
            val endOfPaginationReached = repos.isEmpty()

            database.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    database.remoteKeysDao().clearRemoteKeys()
                    database.moviesDao().clearRepos()
                }
                val prevKey = if (page == MOVIES_STARTING_PAGE_INDEX) null else page.minus(1)
                val nextKey = if ((endOfPaginationReached)) null else page.plus(1)

                val keys = repos.map {
                    RemoteKeysEntity(
                        repoId = it.id,
                        prevKey = prevKey,
                        nextKey = nextKey
                    )
                }
                database.remoteKeysDao().insertAll(keys)
                repos.map {
                    database.moviesDao().insertMovies(it)
                }
            }
            return MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)

        } catch (exception: IOException) {
            return MediatorResult.Error(exception)
        } catch (exception: HttpException) {
            return MediatorResult.Error(exception)
        }
    }

    private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, ResultMovies>): RemoteKeysEntity? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let {
                it.id?.let { id ->
                    database.remoteKeysDao().remoteKeysRepoId(id.toLong())
                }
            }
    }

    private suspend fun getRemoteKeyForFirstItem(state: PagingState<Int, ResultMovies>): RemoteKeysEntity? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let {
                it.id?.let { id ->
                    database.remoteKeysDao().remoteKeysRepoId(id.toLong())
                }
            }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(state: PagingState<Int, ResultMovies>): RemoteKeysEntity? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { repoId ->
                database.remoteKeysDao().remoteKeysRepoId(repoId.toLong())
            }
        }
    }

    companion object {
        const val MOVIES_STARTING_PAGE_INDEX = 1
    }
}