package com.centaury.cataloguemovie.data.local;

import android.os.AsyncTask;

import androidx.paging.DataSource;

import com.centaury.cataloguemovie.data.local.db.CatalogueDao;
import com.centaury.cataloguemovie.data.local.entity.MovieEntity;
import com.centaury.cataloguemovie.data.local.entity.TVShowEntity;

import java.util.concurrent.ExecutionException;

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

    public DataSource.Factory<Integer, MovieEntity> getAllFavMovies() {
        return catalogueDao.getAllFavMovies();
    }

    public MovieEntity getFavMovieById(int id) throws ExecutionException, InterruptedException {
        return new getMovieIdAsync(catalogueDao).execute(id).get();
    }

    public void insertFavMovie(MovieEntity movie) {
        new insertMoviesAsync(catalogueDao).execute(movie);
    }

    public void deleteFavMovie(MovieEntity movie) {
        new deleteMoviesAsync(catalogueDao).execute(movie);
    }

    public DataSource.Factory<Integer, TVShowEntity> getAllFavTVShows() {
        return catalogueDao.getAllFavTVShow();
    }

    public TVShowEntity getFavTVShowById(int id) throws ExecutionException, InterruptedException {
        return new getTVShowIdAsync(catalogueDao).execute(id).get();
    }

    public void insertFavTVShow(TVShowEntity tvshow) {
        new insertTVShowsAsync(catalogueDao).execute(tvshow);
    }

    public void deleteFavTVShow(TVShowEntity tvshow) {
        new deleteTVShowsAsync(catalogueDao).execute(tvshow);
    }

    private static class getMovieIdAsync extends AsyncTask<Integer, Void, MovieEntity> {

        private CatalogueDao catalogueDao;

        public getMovieIdAsync(CatalogueDao catalogueDao) {
            this.catalogueDao = catalogueDao;
        }

        @Override
        protected MovieEntity doInBackground(Integer... integers) {
            return catalogueDao.getFavMovieById(integers[0]);
        }
    }

    private static class getTVShowIdAsync extends AsyncTask<Integer, Void, TVShowEntity> {

        private CatalogueDao catalogueDao;

        public getTVShowIdAsync(CatalogueDao catalogueDao) {
            this.catalogueDao = catalogueDao;
        }

        @Override
        protected TVShowEntity doInBackground(Integer... integers) {
            return catalogueDao.getFavTVShowById(integers[0]);
        }
    }

    private static class insertMoviesAsync extends AsyncTask<MovieEntity, Void, Long> {

        private CatalogueDao catalogueDao;

        public insertMoviesAsync(CatalogueDao catalogueDao) {
            this.catalogueDao = catalogueDao;
        }

        @Override
        protected Long doInBackground(MovieEntity... movieEntities) {
            return catalogueDao.insertFavMovie(movieEntities[0]);
        }
    }

    private static class insertTVShowsAsync extends AsyncTask<TVShowEntity, Void, Long> {

        private CatalogueDao catalogueDao;

        public insertTVShowsAsync(CatalogueDao catalogueDao) {
            this.catalogueDao = catalogueDao;
        }

        @Override
        protected Long doInBackground(TVShowEntity... tvShowEntities) {
            return catalogueDao.insertFavTVShow(tvShowEntities[0]);
        }
    }

    private static class deleteMoviesAsync extends AsyncTask<MovieEntity, Void, Void> {

        private CatalogueDao catalogueDao;

        public deleteMoviesAsync(CatalogueDao catalogueDao) {
            this.catalogueDao = catalogueDao;
        }

        @Override
        protected Void doInBackground(MovieEntity... movieEntities) {
            catalogueDao.deleteFavMovie(movieEntities[0]);
            return null;
        }
    }

    private static class deleteTVShowsAsync extends AsyncTask<TVShowEntity, Void, Void> {

        private CatalogueDao catalogueDao;

        public deleteTVShowsAsync(CatalogueDao catalogueDao) {
            this.catalogueDao = catalogueDao;
        }

        @Override
        protected Void doInBackground(TVShowEntity... tvShowEntities) {
            catalogueDao.deleteFavTVShow(tvShowEntities[0]);
            return null;
        }
    }
}
