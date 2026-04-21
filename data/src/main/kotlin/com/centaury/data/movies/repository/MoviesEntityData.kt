package com.centaury.data.movies.repository

import androidx.paging.PagingSource
import com.centaury.data.movies.repository.source.local.entity.MovieEntity
import com.centaury.data.movies.repository.source.network.result.DetailMovieResponse
import com.centaury.data.movies.repository.source.network.result.GenreMovieResponse
import com.centaury.data.movies.repository.source.network.result.MovieResponse
import com.centaury.data.movies.repository.source.network.result.SearchMovieResponse
import kotlinx.coroutines.flow.Flow

/**
 * @Author Centaury (alfa.arnialfa@gmail.com)
 * Created by Centaury on 7/3/2020.
 */
interface MoviesEntityData {

    suspend fun discoveryMovies(page: Int): MovieResponse

    suspend fun detailMovie(movieId: Int): DetailMovieResponse

    suspend fun genreMovies(): GenreMovieResponse

    suspend fun searchMoviesAll(query: String, page: Int): SearchMovieResponse

    fun getAllFavoriteMovie(): PagingSource<Int, MovieEntity>

    fun getFavoriteMovieById(id: Int): Flow<MovieEntity?>

    suspend fun insertFavoriteMovie(movieEntity: MovieEntity)

    suspend fun deleteFavoriteMovie(movieEntity: MovieEntity)
}
