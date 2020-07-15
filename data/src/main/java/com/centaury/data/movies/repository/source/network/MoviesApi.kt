package com.centaury.data.movies.repository.source.network

import com.centaury.data.movies.repository.source.network.result.detail.DetailMovieResponse
import com.centaury.data.movies.repository.source.network.result.movie.MovieResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * @Author Centaury (alfa.arnialfa@gmail.com)
 * Created by Centaury on 7/3/2020.
 */
interface MoviesApi {

    @GET("discover/movie")
    fun discoveryMovies(): Observable<MovieResponse>

    @GET("movie/{movie_id}")
    fun detailMovie(@Path("movie_id") movieId: String): Observable<DetailMovieResponse>
}