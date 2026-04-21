package com.centaury.data.movies.repository.source.local

import androidx.paging.PagingSource
import com.centaury.data.db.CatalogueDatabase
import com.centaury.data.movies.repository.MoviesEntityData
import com.centaury.data.movies.repository.source.local.entity.MovieEntity
import com.centaury.data.movies.repository.source.network.result.DetailMovieResponse
import com.centaury.data.movies.repository.source.network.result.GenreMovieResponse
import com.centaury.data.movies.repository.source.network.result.MovieResponse
import com.centaury.data.movies.repository.source.network.result.SearchMovieResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

/**
 * @Author Centaury (alfa.arnialfa@gmail.com)
 * Created by Centaury on 7/3/2020.
 */
@Singleton
class LocalMoviesEntityData @Inject constructor(
    private val catalogueDatabase: CatalogueDatabase
) : MoviesEntityData {

    override suspend fun discoveryMovies(page: Int): MovieResponse {
        throw UnsupportedOperationException("Discovery movies not supported in local source")
    }

    override suspend fun detailMovie(movieId: Int): DetailMovieResponse {
        throw UnsupportedOperationException("Detail movie not supported in local source")
    }

    override suspend fun genreMovies(): GenreMovieResponse {
        throw UnsupportedOperationException("Genre movies not supported in local source")
    }

    override suspend fun searchMoviesAll(query: String, page: Int): SearchMovieResponse {
        throw UnsupportedOperationException("Search movies not supported in local source")
    }

    override fun getAllFavoriteMovie(): PagingSource<Int, MovieEntity> =
        catalogueDatabase.movieDao().loadAllMovies()

    override fun getFavoriteMovieById(id: Int): Flow<MovieEntity?> =
        catalogueDatabase.movieDao().loadMovieById(id)

    override suspend fun insertFavoriteMovie(movieEntity: MovieEntity) =
        catalogueDatabase.movieDao().insertMovie(movieEntity)

    override suspend fun deleteFavoriteMovie(movieEntity: MovieEntity) =
        catalogueDatabase.movieDao().deleteMovie(movieEntity)
}
