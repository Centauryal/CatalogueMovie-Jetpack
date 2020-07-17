package com.centaury.data.movies.repository.source.network

import com.centaury.data.movies.repository.source.network.result.MovieResponse
import io.reactivex.Observable
import retrofit2.http.GET

/**
 * @Author Centaury (alfa.arnialfa@gmail.com)
 * Created by Centaury on 7/3/2020.
 */
interface MoviesApi {

    @GET("discover/movie")
    fun discoveryMovies(): Observable<MovieResponse>
}