package com.centaury.data.movies.repository

import com.centaury.data.movies.repository.source.network.result.MovieResponse
import io.reactivex.Observable

/**
 * @Author Centaury (alfa.arnialfa@gmail.com)
 * Created by Centaury on 7/3/2020.
 */
interface MoviesEntityData {

    fun discoveryMovies(): Observable<MovieResponse>
}