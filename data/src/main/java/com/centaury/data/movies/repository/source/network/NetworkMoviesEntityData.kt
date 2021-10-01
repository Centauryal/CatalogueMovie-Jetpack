package com.centaury.data.movies.repository.source.network

import com.centaury.data.movies.repository.MoviesEntityData
import com.centaury.data.movies.repository.source.local.entity.MovieEntity
import com.centaury.data.movies.repository.source.network.result.DetailMovieResponse
import com.centaury.data.movies.repository.source.network.result.GenreMovieResponse
import com.centaury.data.movies.repository.source.network.result.MovieResponse
import com.centaury.data.movies.repository.source.network.result.SearchMovieResponse
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import javax.inject.Inject

/**
 * @Author Centaury (alfa.arnialfa@gmail.com)
 * Created by Centaury on 7/3/2020.
 */
class NetworkMoviesEntityData @Inject constructor(
    private val moviesApi: MoviesApi
) : MoviesEntityData {
    override fun discoveryMovies(): Observable<MovieResponse> = moviesApi.discoveryMovies()

    override fun detailMovie(movieId: Int): Observable<DetailMovieResponse> =
        moviesApi.detailMovie(movieId)

    override fun genreMovies(): Observable<GenreMovieResponse> = moviesApi.genreMovies()

    override fun searchMoviesAll(query: String): Observable<SearchMovieResponse> =
        moviesApi.searchMoviesAll(query)

    override fun getAllFavoriteMovie(): Flowable<List<MovieEntity>> {
        TODO("Not yet implemented")
    }

    override fun getFavoriteMovieById(id: Int): Flowable<MovieEntity> {
        TODO("Not yet implemented")
    }

    override fun insertFavoriteMovie(movieEntity: MovieEntity): Completable {
        TODO("Not yet implemented")
    }

    override fun deleteFavoriteMovie(movieEntity: MovieEntity): Completable {
        TODO("Not yet implemented")
    }
}