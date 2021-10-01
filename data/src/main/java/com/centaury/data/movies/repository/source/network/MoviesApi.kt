package com.centaury.data.movies.repository.source.network

import com.centaury.data.movies.repository.source.network.result.DetailMovieResponse
import com.centaury.data.movies.repository.source.network.result.GenreMovieResponse
import com.centaury.data.movies.repository.source.network.result.MovieResponse
import com.centaury.data.movies.repository.source.network.result.SearchMovieResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * @Author Centaury (alfa.arnialfa@gmail.com)
 * Created by Centaury on 7/3/2020.
 */
interface MoviesApi {

    @GET("discover/movie")
    fun discoveryMovies(): Observable<MovieResponse>

    @GET("movie/{movie_id}")
    fun detailMovie(@Path("movie_id") movieId: Int): Observable<DetailMovieResponse>

    @GET("genre/movie/list")
    fun genreMovies(): Observable<GenreMovieResponse>

    @GET("search/movie")
    fun searchMoviesAll(@Query("query") query: String): Observable<SearchMovieResponse>
}