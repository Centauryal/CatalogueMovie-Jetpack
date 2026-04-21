package com.centaury.data.movies.repository.source.network

import com.centaury.data.movies.repository.source.network.result.DetailMovieResponse
import com.centaury.data.movies.repository.source.network.result.GenreMovieResponse
import com.centaury.data.movies.repository.source.network.result.MovieResponse
import com.centaury.data.movies.repository.source.network.result.SearchMovieResponse
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * @Author Centaury (alfa.arnialfa@gmail.com)
 * Created by Centaury on 7/3/2020.
 */
interface MoviesApi {

    @Headers("Cache-Control: max-age=64800")
    @GET("discover/movie")
    suspend fun discoveryMovies(
        @Query("page") page: Int = 1
    ): MovieResponse

    @GET("movie/{movie_id}")
    suspend fun detailMovie(@Path("movie_id") movieId: Int): DetailMovieResponse

    @GET("genre/movie/list")
    suspend fun genreMovies(): GenreMovieResponse

    @GET("search/movie")
    suspend fun searchMoviesAll(
        @Query("query") query: String,
        @Query("page") page: Int = 1
    ): SearchMovieResponse
}