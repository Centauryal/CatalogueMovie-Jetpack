package com.centaury.data.movies.repository.source.local

import com.centaury.data.db.CatalogueDatabase
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
class LocalMoviesEntityData @Inject constructor(
    private val catalogueDatabase: CatalogueDatabase
) : MoviesEntityData {
    override fun discoveryMovies(): Observable<MovieResponse> {
        TODO("Not yet implemented")
    }

    override fun detailMovie(movieId: Int): Observable<DetailMovieResponse> {
        TODO("Not yet implemented")
    }

    override fun genreMovies(): Observable<GenreMovieResponse> {
        TODO("Not yet implemented")
    }

    override fun searchMoviesAll(query: String): Observable<SearchMovieResponse> {
        TODO("Not yet implemented")
    }

    override fun getAllFavoriteMovie(): Flowable<List<MovieEntity>> =
        catalogueDatabase.movieDao().loadAllMovies()

    override fun getFavoriteMovieById(id: Int): Flowable<MovieEntity> =
        catalogueDatabase.movieDao().loadMovieById(id)

    override fun insertFavoriteMovie(movieEntity: MovieEntity): Completable =
        catalogueDatabase.movieDao().insertMovie(movieEntity)

    override fun deleteFavoriteMovie(movieEntity: MovieEntity): Completable =
        catalogueDatabase.movieDao().deleteMovie(movieEntity)

}