package com.centaury.cataloguemovie.data;

import androidx.lifecycle.LiveData;

import com.centaury.cataloguemovie.data.local.entity.GenreMovieEntity;
import com.centaury.cataloguemovie.data.local.entity.GenreTVShowEntity;
import com.centaury.cataloguemovie.data.local.entity.MovieEntity;
import com.centaury.cataloguemovie.data.local.entity.TVShowEntity;
import com.centaury.cataloguemovie.vo.Resource;

import java.util.List;

/**
 * Created by Centaury on 10/25/2019.
 */
public interface CatalogueDataSource {

    LiveData<Resource<List<MovieEntity>>> getMovies(String language);

    LiveData<Resource<List<MovieEntity>>> getFavoritedMovies();

    LiveData<Resource<MovieEntity>> getDetailMovie(String movieId, String language);

    LiveData<Resource<List<GenreMovieEntity>>> getGenreMovie(String language);

    LiveData<Resource<List<TVShowEntity>>> getTVShows(String language);

    LiveData<Resource<List<TVShowEntity>>> getFavoritedTVShows();

    LiveData<Resource<TVShowEntity>> getDetailTVShow(String tvshowId, String language);

    LiveData<Resource<List<GenreTVShowEntity>>> getGenreTVShow(String language);

    void setFavoriteMovie(MovieEntity movieEntity, boolean state);

    void setFavoriteTVShow(TVShowEntity tvShowEntity, boolean state);
}
