package com.centaury.domain.movies

import com.centaury.domain.model.*
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable

/**
 * @Author Centaury (alfa.arnialfa@gmail.com)
 * Created by Centaury on 7/3/2020.
 */
interface MoviesRepository {

    fun getDiscoveryMovies(): Observable<List<Movie>>

    fun getDetailMovie(movieId: Int): Observable<Detail>

    fun getGenreMovies(): Observable<List<Genre>>

    fun getSearchMoviesAll(query: String): Observable<List<Search>>

    fun getAllFavoriteMovie(): Flowable<List<MoviesDB>>

    fun getFavoriteMovieById(id: Int): Flowable<MoviesDB>

    fun insertFavoriteMovie(movie: MoviesDB): Completable

    fun deleteFavoriteMovie(movie: MoviesDB): Completable
}