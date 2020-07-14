package com.centaury.data.movies.repository.source.network

import com.centaury.data.movies.repository.MoviesEntityData
import com.centaury.data.movies.repository.source.network.result.detail.DetailMovieResponse
import com.centaury.data.movies.repository.source.network.result.genre.GenreResponse
import com.centaury.data.movies.repository.source.network.result.movie.MovieResponse
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

    override fun detailMovie(movieId: String): Observable<DetailMovieResponse> =
        moviesApi.detailMovie(movieId)

    override fun genreMovies(): Observable<GenreResponse> = moviesApi.genreMovies()
}