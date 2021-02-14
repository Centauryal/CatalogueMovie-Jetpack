package com.centaury.data.movies.repository

import com.centaury.data.common.Source
import com.centaury.data.movies.mapper.MoviesEntityMapper
import com.centaury.data.movies.mapper.MoviesResultMapper
import com.centaury.data.movies.repository.source.MoviesDataFactory
import com.centaury.domain.movies.MoviesRepository
import com.centaury.domain.movies.model.Movie
import com.centaury.domain.movies.model.MoviesDB
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import javax.inject.Inject

/**
 * @Author Centaury (alfa.arnialfa@gmail.com)
 * Created by Centaury on 7/3/2020.
 */
class MoviesEntityRepository @Inject constructor(
    private val moviesDataFactory: MoviesDataFactory,
    private val moviesResultMapper: MoviesResultMapper,
    private val moviesEntityMapper: MoviesEntityMapper
) : MoviesRepository {

    override fun getDiscoveryMovies(): Observable<List<Movie>> =
        createMovieData().discoveryMovies().map { moviesResultMapper.transformMovie(it) }

    override fun getAllFavoriteMovie(): Flowable<List<MoviesDB>> =
        createEntityMovieData().getAllFavoriteMovie()
            .map { moviesEntityMapper.transformEntityMovie(it) }

    override fun getFavoriteMovieById(id: Int): Flowable<MoviesDB> =
        createEntityMovieData().getFavoriteMovieById(id)
            .map { moviesEntityMapper.transformEntityMovieById(it) }

    override fun insertFavoriteMovie(movie: MoviesDB): Completable =
        createEntityMovieData().insertFavoriteMovie(moviesEntityMapper.transformMovieToEntity(movie))

    override fun deleteFavoriteMovie(movie: MoviesDB): Completable =
        createEntityMovieData().deleteFavoriteMovie(moviesEntityMapper.transformMovieToEntity(movie))

    private fun createMovieData(): MoviesEntityData =
        moviesDataFactory.createData(Source.NETWORK)

    private fun createEntityMovieData(): MoviesEntityData =
        moviesDataFactory.createData(Source.LOCAL)
}