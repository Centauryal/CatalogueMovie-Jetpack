package com.centaury.domain.movies

import androidx.paging.PagingData
import com.centaury.domain.model.*
import kotlinx.coroutines.flow.Flow

/**
 * @Author Centaury (alfa.arnialfa@gmail.com)
 * Created by Centaury on 7/3/2020.
 */
interface MoviesRepository {

    fun getDiscoveryMovies(): Flow<PagingData<Movie>>

    suspend fun getDetailMovie(movieId: Int): Detail

    suspend fun getGenreMovies(): List<Genre>

    fun getSearchMoviesAll(query: String): Flow<PagingData<Search>>

    fun getAllFavoriteMovie(): Flow<PagingData<MoviesDB>>

    fun getFavoriteMovieById(id: Int): Flow<MoviesDB?>

    suspend fun insertFavoriteMovie(movie: MoviesDB)

    suspend fun deleteFavoriteMovie(movie: MoviesDB)
}
