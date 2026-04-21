package com.centaury.data.movies.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.centaury.data.common.Source
import com.centaury.data.movies.mapper.MoviesEntityMapper
import com.centaury.data.movies.mapper.MoviesResultMapper
import com.centaury.data.movies.repository.source.MoviesDataFactory
import com.centaury.data.movies.repository.source.network.MoviePagingSource
import com.centaury.data.movies.repository.source.network.SearchPagingSource
import com.centaury.domain.model.Detail
import com.centaury.domain.model.Genre
import com.centaury.domain.model.Movie
import com.centaury.domain.model.MoviesDB
import com.centaury.domain.model.Search
import com.centaury.domain.movies.MoviesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

/**
 * @Author Centaury (alfa.arnialfa@gmail.com)
 * Created by Centaury on 7/3/2020.
 */
@Singleton
class MoviesEntityRepository @Inject constructor(
    private val moviesDataFactory: MoviesDataFactory,
    private val moviesResultMapper: MoviesResultMapper,
    private val moviesEntityMapper: MoviesEntityMapper
) : MoviesRepository {

    override fun getDiscoveryMovies(): Flow<PagingData<Movie>> = Pager(
        config = PagingConfig(
            pageSize = MAX_PAGE_SIZE,
            enablePlaceholders = false
        ),
        pagingSourceFactory = {
            MoviePagingSource(createMovieData(), moviesResultMapper)
        }
    ).flow

    override suspend fun getDetailMovie(movieId: Int): Detail =
        moviesResultMapper.transformDetailMovie(createMovieData().detailMovie(movieId))

    override suspend fun getGenreMovies(): List<Genre> =
        moviesResultMapper.transformGenreMovie(createMovieData().genreMovies())

    override fun getSearchMoviesAll(query: String): Flow<PagingData<Search>> = Pager(
        config = PagingConfig(
            pageSize = MAX_PAGE_SIZE,
            enablePlaceholders = false
        ),
        pagingSourceFactory = {
            SearchPagingSource(createMovieData(), moviesResultMapper, query)
        }
    ).flow

    override fun getAllFavoriteMovie(): Flow<PagingData<MoviesDB>> = Pager(
        config = PagingConfig(
            pageSize = MAX_PAGE_SIZE,
            enablePlaceholders = false
        ),
        pagingSourceFactory = { createEntityMovieData().getAllFavoriteMovie() }
    ).flow.map { pagingData ->
        pagingData.map { moviesEntityMapper.transformEntityMovieById(it) }
    }

    override fun getFavoriteMovieById(id: Int): Flow<MoviesDB?> =
        createEntityMovieData().getFavoriteMovieById(id).map { entity ->
            entity?.let { moviesEntityMapper.transformEntityMovieById(it) }
        }

    override suspend fun insertFavoriteMovie(movie: MoviesDB) {
        createEntityMovieData().insertFavoriteMovie(moviesEntityMapper.transformMovieToEntity(movie))
    }

    override suspend fun deleteFavoriteMovie(movie: MoviesDB) {
        createEntityMovieData().deleteFavoriteMovie(moviesEntityMapper.transformMovieToEntity(movie))
    }

    private fun createMovieData(): MoviesEntityData =
        moviesDataFactory.createData(Source.NETWORK)

    private fun createEntityMovieData(): MoviesEntityData =
        moviesDataFactory.createData(Source.LOCAL)

    companion object {
        private const val MAX_PAGE_SIZE = 10
    }
}
