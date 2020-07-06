package com.centaury.data.movies.repository.source.local

import com.centaury.cataloguemovie.data.remote.genre.GenreResponse
import com.centaury.data.movies.repository.MoviesEntityData
import com.centaury.data.movies.repository.source.network.result.detail.DetailMovieResponse
import com.centaury.data.movies.repository.source.network.result.movie.MovieResponse
import io.reactivex.Observable
import javax.inject.Inject

/**
 * @Author Centaury (alfa.arnialfa@gmail.com)
 * Created by Centaury on 7/3/2020.
 */
class LocalMoviesEntityData @Inject constructor(
) : MoviesEntityData {
    override fun discoveryMovies(): Observable<MovieResponse> {
        TODO("Not yet implemented")
    }

    override fun detailMovie(movieId: String): Observable<DetailMovieResponse> {
        TODO("Not yet implemented")
    }

    override fun genreMovies(): Observable<GenreResponse> {
        TODO("Not yet implemented")
    }

}