package com.enesselcuk.watchmovies.source.remote.apiService

import com.enesselcuk.watchmovies.BuildConfig.API_KEY
import com.enesselcuk.watchmovies.source.remote.response.categorys.MoviesResponse
import com.enesselcuk.watchmovies.source.remote.response.detail.DetailResponse
import com.enesselcuk.watchmovies.source.remote.response.videos.VideoResponse
import retrofit2.http.*


//curl --request GET \
//  --url 'https://api.themoviedb.org/3/movie/76341' \
//  --header 'Authorization: Bearer <<access_token>>' \
//  --header 'Content-Type: application/json;charset=utf-8'
interface MoviesService {

//    @Headers("Content-Type: application/json;charset=utf-8")
//    @GET("3/movie/{movies}")
//    suspend fun getMoviesAuth(
//        @Header("Authorization") bearer: String? = null,
//        @Path("movies") movies: String? = null,
////        @Query("api_key") api_key: String = API_KEY,
//        @Query("language") language: String? = null,
//        @Query("page") page: Int? = null,
//    ): MoviesResponse


    @GET("3/movie/{movies}")
    suspend fun getMovies(
        @Path("movies") movies: String? = null,
        @Query("api_key") api_key: String = API_KEY,
        @Query("language") language: String? = null,
        @Query("page") page: Int? = null,
    ): MoviesResponse

    @GET("3/trending/all/day")
    suspend fun getTrending(
        @Query("api_key") api_key: String = API_KEY,
        @Query("page") page: Int? = null
    ): MoviesResponse

    @GET("3/trending/all/day")
    suspend fun getTrendingToken(
        @Header("Authorization") bearer: String? = "Bearer 8cdbc9aa758cdc9d9fa138848181698c",
        @Header("content-type") content: String? = "application/json;charset=utf-8",
        //     @Query("api_key") api_key: String = API_KEY,
        @Query("page") page: Int? = null
    ): MoviesResponse

    @GET("3/movie/{movie_id}/videos")
    suspend fun getVideos(
        @Path("movie_id") movie_id: Int? = null,
        @Query("api_key") api_key: String = API_KEY,
        @Query("language") language: String? = null
    ): VideoResponse

    @GET("3/movie/{movie_id}")
    suspend fun getDetail(
        @Path("movie_id") movie_id: Int? = null,
        @Query("language") language: String? = null,
        @Query("api_key") api_key: String = API_KEY,
    ): DetailResponse

    @GET("3/search/movie")
    suspend fun getSearch(
        @Query("language") language: String? = null,
        @Query("query") query: String? = null,
        @Query("page") page: Int? = null,
        @Query("api_key") api_key: String = API_KEY,
    ): MoviesResponse

}
