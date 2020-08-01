package com.centaury.data.db.dao

import androidx.room.*
import com.centaury.data.movies.repository.source.local.entity.MovieEntity
import com.centaury.data.movies.repository.source.local.entity.MovieEntity.Companion.COLUMN_MOVIE_ID
import com.centaury.data.movies.repository.source.local.entity.MovieEntity.Companion.TABLE_NAME_MOVIE
import io.reactivex.Completable
import io.reactivex.Flowable

/**
 * @Author Centaury (alfa.arnialfa@gmail.com)
 * Created by Centaury on 7/24/2020.
 */
@Dao
interface MovieDao {

    @Query("SELECT * FROM $TABLE_NAME_MOVIE")
    fun loadAllMovies(): Flowable<List<MovieEntity>>

    @Transaction
    @Query("SELECT * FROM $TABLE_NAME_MOVIE WHERE $COLUMN_MOVIE_ID = :id")
    fun loadMovieById(id: Int): Flowable<MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(vararg movieEntity: MovieEntity): Completable

    @Delete
    fun deleteMovie(vararg movieEntity: MovieEntity): Completable
}