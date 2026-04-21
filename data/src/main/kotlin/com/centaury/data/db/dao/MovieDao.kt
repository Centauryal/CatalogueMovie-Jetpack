package com.centaury.data.db.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.centaury.data.movies.repository.source.local.entity.MovieEntity
import com.centaury.data.movies.repository.source.local.entity.MovieEntity.Companion.COLUMN_MOVIE_ID
import com.centaury.data.movies.repository.source.local.entity.MovieEntity.Companion.TABLE_NAME_MOVIE
import kotlinx.coroutines.flow.Flow

/**
 * @Author Centaury (alfa.arnialfa@gmail.com)
 * Created by Centaury on 7/24/2020.
 */
@Dao
interface MovieDao {

    @Query("SELECT * FROM $TABLE_NAME_MOVIE")
    fun loadAllMovies(): PagingSource<Int, MovieEntity>

    @Query("SELECT * FROM $TABLE_NAME_MOVIE WHERE $COLUMN_MOVIE_ID = :id")
    fun loadMovieById(id: Int): Flow<MovieEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(vararg movieEntity: MovieEntity)

    @Delete
    suspend fun deleteMovie(vararg movieEntity: MovieEntity)
}