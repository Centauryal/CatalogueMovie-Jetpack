package com.centaury.cataloguemovie.data.local.db;

import androidx.annotation.WorkerThread;
import androidx.paging.DataSource;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

import com.centaury.cataloguemovie.data.local.entity.MovieEntity;
import com.centaury.cataloguemovie.data.local.entity.TVShowEntity;

import static com.centaury.cataloguemovie.data.local.entity.MovieEntity.COLUMN_MOVIE_ID;
import static com.centaury.cataloguemovie.data.local.entity.TVShowEntity.COLUMN_TVSHOW_ID;

/**
 * Created by Centaury on 11/20/2019.
 */
@Dao
public interface CatalogueDao {

    @WorkerThread
    @Query("SELECT * FROM movieentities")
    DataSource.Factory<Integer, MovieEntity> getAllFavMovies();

    @Transaction
    @Query("SELECT * FROM movieentities WHERE " + COLUMN_MOVIE_ID + "= :id")
    MovieEntity getFavMovieById(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertFavMovie(MovieEntity movies);

    @Delete
    void deleteFavMovie(MovieEntity movie);

    @WorkerThread
    @Query("SELECT * FROM tvshowentities")
    DataSource.Factory<Integer, TVShowEntity> getAllFavTVShow();

    @Transaction
    @Query("SELECT * FROM tvshowentities WHERE " + COLUMN_TVSHOW_ID + "= :id")
    TVShowEntity getFavTVShowById(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertFavTVShow(TVShowEntity tvshows);

    @Delete
    void deleteFavTVShow(TVShowEntity tvshow);
}
