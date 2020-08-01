package com.centaury.domain.movies

import com.centaury.domain.movies.model.Movie
import com.centaury.domain.movies.model.MoviesEntity
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable

/**
 * @Author Centaury (alfa.arnialfa@gmail.com)
 * Created by Centaury on 7/3/2020.
 */
interface MoviesRepository {

    fun getDiscoveryMovies(): Observable<List<Movie>>

    fun getAllFavoriteMovie(): Flowable<List<MoviesEntity>>

    fun getFavoriteMovieById(id: Int): Flowable<MoviesEntity>

    fun insertFavoriteMovie(movie: MoviesEntity): Completable

    fun deleteFavoriteMovie(movie: MoviesEntity): Completable
}