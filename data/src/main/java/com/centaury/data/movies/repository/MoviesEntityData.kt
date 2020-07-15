package com.centaury.data.movies.repository

import com.centaury.data.movies.repository.source.network.result.detail.DetailMovieResponse
import com.centaury.data.movies.repository.source.network.result.movie.MovieResponse
import io.reactivex.Observable

/**
 * @Author Centaury (alfa.arnialfa@gmail.com)
 * Created by Centaury on 7/3/2020.
 */
interface MoviesEntityData {

    fun discoveryMovies(): Observable<MovieResponse>

    fun detailMovie(movieId: String): Observable<DetailMovieResponse>
}