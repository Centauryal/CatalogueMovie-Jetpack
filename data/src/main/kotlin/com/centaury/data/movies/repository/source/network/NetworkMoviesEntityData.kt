package com.centaury.data.movies.repository.source.network

import androidx.paging.PagingSource
import com.centaury.data.movies.repository.MoviesEntityData
import com.centaury.data.movies.repository.source.local.entity.MovieEntity
import com.centaury.data.movies.repository.source.network.result.DetailMovieResponse
import com.centaury.data.movies.repository.source.network.result.GenreMovieResponse
import com.centaury.data.movies.repository.source.network.result.MovieResponse
import com.centaury.data.movies.repository.source.network.result.SearchMovieResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * @Author Centaury (alfa.arnialfa@gmail.com)
 * Created by Centaury on 7/3/2020.
 */
class NetworkMoviesEntityData @Inject constructor(
    private val moviesApi: MoviesApi
) : MoviesEntityData {
    override suspend fun discoveryMovies(page: Int): MovieResponse = moviesApi.discoveryMovies(page)

    override suspend fun detailMovie(movieId: Int): DetailMovieResponse =
        moviesApi.detailMovie(movieId)

    override suspend fun genreMovies(): GenreMovieResponse = moviesApi.genreMovies()

    override suspend fun searchMoviesAll(query: String, page: Int): SearchMovieResponse =
        moviesApi.searchMoviesAll(query, page)

    override fun getAllFavoriteMovie(): PagingSource<Int, MovieEntity> {
        throw UnsupportedOperationException("Favorite movies not supported in network source")
    }

    override fun getFavoriteMovieById(id: Int): Flow<MovieEntity?> {
        throw UnsupportedOperationException("Favorite movie by id not supported in network source")
    }

    override suspend fun insertFavoriteMovie(movieEntity: MovieEntity) {
        throw UnsupportedOperationException("Insert favorite movie not supported in network source")
    }

    override suspend fun deleteFavoriteMovie(movieEntity: MovieEntity) {
        throw UnsupportedOperationException("Delete favorite movie not supported in network source")
    }
}
