package com.centaury.domain.movies

import com.centaury.domain.movies.model.Movie
import com.centaury.domain.movies.model.MoviesDB
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable

/**
 * @Author Centaury (alfa.arnialfa@gmail.com)
 * Created by Centaury on 7/3/2020.
 */
interface MoviesRepository {

    fun getDiscoveryMovies(): Observable<List<Movie>>

    fun getAllFavoriteMovie(): Flowable<List<MoviesDB>>

    fun getFavoriteMovieById(id: Int): Flowable<MoviesDB>

    fun insertFavoriteMovie(movie: MoviesDB): Completable

    fun deleteFavoriteMovie(movie: MoviesDB): Completable
}