package com.centaury.cataloguemovie.data;

import androidx.lifecycle.LiveData;
import androidx.paging.PagedList;

import com.centaury.cataloguemovie.data.local.entity.MovieEntity;
import com.centaury.cataloguemovie.data.local.entity.TVShowEntity;
import com.centaury.cataloguemovie.data.remote.detail.movie.DetailMovieResponse;
import com.centaury.cataloguemovie.data.remote.detail.tvshow.DetailTVShowResponse;
import com.centaury.cataloguemovie.data.remote.genre.GenresItem;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by Centaury on 10/25/2019.
 */
public interface CatalogueDataSource {

    LiveData<DetailMovieResponse> getDetailMovie(String movieId, String language);

    LiveData<List<GenresItem>> getGenreMovie(String language);

    LiveData<DetailTVShowResponse> getDetailTVShow(String tvshowId, String language);

    LiveData<List<GenresItem>> getGenreTVShow(String language);

    LiveData<PagedList<MovieEntity>> getFavoriteMovies();

    MovieEntity getDetailFavMovie(int movieId) throws ExecutionException, InterruptedException;

    void insertFavMovie(MovieEntity movieEntity);

    void deleteFavMovie(MovieEntity movieEntity);

    LiveData<PagedList<TVShowEntity>> getFavoriteTVShows();

    TVShowEntity getDetailFavTVShow(int tvshowId) throws ExecutionException, InterruptedException;

    void insertFavTVShow(TVShowEntity tvShowEntity);

    void deleteFavTVShow(TVShowEntity tvShowEntity);

}
