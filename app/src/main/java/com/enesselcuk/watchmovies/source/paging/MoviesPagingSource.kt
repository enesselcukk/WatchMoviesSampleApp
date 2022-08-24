package com.enesselcuk.watchmovies.source.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.enesselcuk.watchmovies.source.remote.apiService.MoviesService
import com.enesselcuk.watchmovies.source.remote.response.categorys.ResultMovies
import retrofit2.HttpException

class MoviesPagingSource(
    private val api: MoviesService? = null,
    private val categoryName: String? = null,
    private val language: String? = null,
) : PagingSource<Int, ResultMovies>() {
    override fun getRefreshKey(state: PagingState<Int, ResultMovies>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ResultMovies> {
        val position = params.key ?: STARTING_PAGE_INDEX
        return try {
            val responseCategory =
                api?.getSearch(language = language, query = categoryName, page = params.loadSize)
            val repos = responseCategory?.results
            val nextKey = if (repos?.isEmpty() == true) {
                null
            } else {
                position.plus(1)
            }

            LoadResult.Page(
                data = responseCategory!!.results,
                prevKey = if (position == STARTING_PAGE_INDEX) null else position.minus(1),
                nextKey = nextKey
            )
        } catch (ex: Exception) {
            return LoadResult.Error(ex)
        } catch (ex: HttpException) {
            return LoadResult.Error(ex)
        }
    }

    companion object {
        const val STARTING_PAGE_INDEX = 1
    }

}