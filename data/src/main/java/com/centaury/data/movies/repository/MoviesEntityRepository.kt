package com.centaury.data.movies.repository

import com.centaury.data.common.Source
import com.centaury.data.movies.mapper.MoviesResultMapper
import com.centaury.data.movies.repository.source.MoviesDataFactory
import com.centaury.domain.movies.MoviesRepository
import com.centaury.domain.movies.model.Detail
import com.centaury.domain.movies.model.Genre
import com.centaury.domain.movies.model.Movie
import io.reactivex.Observable
import javax.inject.Inject

/**
 * @Author Centaury (alfa.arnialfa@gmail.com)
 * Created by Centaury on 7/3/2020.
 */
class MoviesEntityRepository @Inject constructor(
    private val moviesDataFactory: MoviesDataFactory,
    private val moviesResultMapper: MoviesResultMapper
) : MoviesRepository {

    override fun getDiscoveryMovies(): Observable<List<Movie>> =
        createMovieData().discoveryMovies().map { moviesResultMapper.transformMovie(it) }

    override fun getDetailMovie(movieId: String): Observable<Detail> {
        TODO("Not yet implemented")
    }

    override fun getGenreMovies(): Observable<List<Genre>> =
        createGenreData().genreMovies().map { moviesResultMapper.transformGenreMovie(it) }

    private fun createMovieData(): MoviesEntityData =
        moviesDataFactory.createData(Source.NETWORK)

    private fun createGenreData(): MoviesEntityData =
        moviesDataFactory.createData(Source.NETWORK)
}