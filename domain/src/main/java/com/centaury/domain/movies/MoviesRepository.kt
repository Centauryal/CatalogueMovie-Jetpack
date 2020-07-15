package com.centaury.domain.movies

import com.centaury.domain.movies.model.Detail
import com.centaury.domain.movies.model.Movie
import io.reactivex.Observable

/**
 * @Author Centaury (alfa.arnialfa@gmail.com)
 * Created by Centaury on 7/3/2020.
 */
interface MoviesRepository {

    fun getDiscoveryMovies(): Observable<List<Movie>>

    fun getDetailMovie(movieId: String): Observable<Detail>
}