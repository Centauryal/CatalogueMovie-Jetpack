package com.centaury.cataloguemovie.data.local.db;

import androidx.annotation.WorkerThread;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.centaury.cataloguemovie.data.local.entity.GenreMovieEntity;
import com.centaury.cataloguemovie.data.local.entity.GenreTVShowEntity;
import com.centaury.cataloguemovie.data.local.entity.MovieEntity;
import com.centaury.cataloguemovie.data.local.entity.TVShowEntity;

import java.util.List;

import static com.centaury.cataloguemovie.data.local.entity.MovieEntity.COLUMN_MOVIE_FAVORITED;
import static com.centaury.cataloguemovie.data.local.entity.MovieEntity.COLUMN_MOVIE_ID;
import static com.centaury.cataloguemovie.data.local.entity.TVShowEntity.COLUMN_TVSHOW_FAVORITED;
import static com.centaury.cataloguemovie.data.local.entity.TVShowEntity.COLUMN_TVSHOW_ID;

/**
 * Created by Centaury on 11/20/2019.
 */
@Dao
public interface CatalogueDao {

    @WorkerThread
    @Query("SELECT * FROM movieentities")
    LiveData<List<MovieEntity>> getAllMovies();

    @WorkerThread
    @Query("SELECT * FROM movieentities WHERE " + COLUMN_MOVIE_FAVORITED + " = 1")
    LiveData<List<MovieEntity>> getFavoritedMovie();

    @Transaction
    @Query("SELECT * FROM movieentities WHERE " + COLUMN_MOVIE_ID + "= :id")
    LiveData<MovieEntity> getMovieById(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] insertMovie(List<MovieEntity> movies);

    @Update
    int updateMovie(MovieEntity movie);

    @WorkerThread
    @Query("SELECT * FROM tvshowentities")
    LiveData<List<TVShowEntity>> getAllTVShow();

    @WorkerThread
    @Query("SELECT * FROM tvshowentities WHERE " + COLUMN_TVSHOW_FAVORITED + " = 1")
    LiveData<List<TVShowEntity>> getFavoritedTVShow();

    @Transaction
    @Query("SELECT * FROM tvshowentities WHERE " + COLUMN_TVSHOW_ID + "= :id")
    LiveData<TVShowEntity> getTVShowById(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] insertTVShow(List<TVShowEntity> tvshows);

    @Update
    int updateTVShow(TVShowEntity tvshow);

    @WorkerThread
    @Query("SELECT * FROM genremovie")
    LiveData<List<GenreMovieEntity>> getGenresMovie();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] insertGenreMovie(List<GenreMovieEntity> genreMovie);

    @WorkerThread
    @Query("SELECT * FROM genretvshow")
    LiveData<List<GenreTVShowEntity>> getGenresTVShow();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] insertGenreTVShow(List<GenreTVShowEntity> genreTVShow);
}
