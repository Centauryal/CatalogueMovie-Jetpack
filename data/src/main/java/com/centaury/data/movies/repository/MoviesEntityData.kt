package com.centaury.data.movies.repository

import com.centaury.data.movies.repository.source.local.entity.MovieEntity
import com.centaury.data.movies.repository.source.network.result.DetailMovieResponse
import com.centaury.data.movies.repository.source.network.result.GenreMovieResponse
import com.centaury.data.movies.repository.source.network.result.MovieResponse
import com.centaury.data.movies.repository.source.network.result.SearchMovieResponse
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable

/**
 * @Author Centaury (alfa.arnialfa@gmail.com)
 * Created by Centaury on 7/3/2020.
 */
interface MoviesEntityData {

    fun discoveryMovies(): Observable<MovieResponse>

    fun detailMovie(movieId: Int): Observable<DetailMovieResponse>

    fun genreMovies(): Observable<GenreMovieResponse>

    fun searchMoviesAll(query: String): Observable<SearchMovieResponse>

    fun getAllFavoriteMovie(): Flowable<List<MovieEntity>>

    fun getFavoriteMovieById(id: Int): Flowable<MovieEntity>

    fun insertFavoriteMovie(movieEntity: MovieEntity): Completable

    fun deleteFavoriteMovie(movieEntity: MovieEntity): Completable
}