package com.centaury.cataloguemovie.data.local;

import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;

import com.centaury.cataloguemovie.data.local.db.CatalogueDao;
import com.centaury.cataloguemovie.data.local.entity.GenreMovieEntity;
import com.centaury.cataloguemovie.data.local.entity.GenreTVShowEntity;
import com.centaury.cataloguemovie.data.local.entity.MovieEntity;
import com.centaury.cataloguemovie.data.local.entity.TVShowEntity;

import java.util.List;

/**
 * Created by Centaury on 11/20/2019.
 */
public class LocalRepository {

    private static LocalRepository INSTANCE;
    private final CatalogueDao catalogueDao;

    public LocalRepository(CatalogueDao catalogueDao) {
        this.catalogueDao = catalogueDao;
    }

    public static LocalRepository getInstance(CatalogueDao catalogueDao) {
        if (INSTANCE == null) {
            INSTANCE = new LocalRepository(catalogueDao);
        }
        return INSTANCE;
    }

    public LiveData<List<MovieEntity>> getAllMovies() {
        return catalogueDao.getAllMovies();
    }

    public DataSource.Factory<Integer, MovieEntity> getFavoritedMovies() {
        return catalogueDao.getFavoritedMovie();
    }

    public LiveData<MovieEntity> getMovieById(final int id) {
        return catalogueDao.getMovieById(id);
    }

    public void insertMovie(List<MovieEntity> movie) {
        catalogueDao.insertMovie(movie);
    }

    public void updateFavoritedMovie(MovieEntity movie, boolean newState) {
        movie.setMovieFavorited(newState);
        catalogueDao.updateMovie(movie);
    }

    public LiveData<List<TVShowEntity>> getAllTVShows() {
        return catalogueDao.getAllTVShow();
    }

    public DataSource.Factory<Integer, TVShowEntity> getFavoritedTVShows() {
        return catalogueDao.getFavoritedTVShow();
    }

    public LiveData<TVShowEntity> getTVShowById(final int id) {
        return catalogueDao.getTVShowById(id);
    }

    public void insertTVShow(List<TVShowEntity> tvshow) {
        catalogueDao.insertTVShow(tvshow);
    }

    public void updateFavoritedTVShow(TVShowEntity tvshow, boolean newState) {
        tvshow.setTvshowFavorited(newState);
        catalogueDao.updateTVShow(tvshow);
    }

    public LiveData<List<GenreMovieEntity>> getAllGenresMovie() {
        return catalogueDao.getGenresMovie();
    }

    public void insertGenresMovie(List<GenreMovieEntity> genreMovie) {
        catalogueDao.insertGenreMovie(genreMovie);
    }

    public LiveData<List<GenreTVShowEntity>> getAllGenresTVShow() {
        return catalogueDao.getGenresTVShow();
    }

    public void insertGenresTVShow(List<GenreTVShowEntity> genreTVShow) {
        catalogueDao.insertGenreTVShow(genreTVShow);
    }
}
