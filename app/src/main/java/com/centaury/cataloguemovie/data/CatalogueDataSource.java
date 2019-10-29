package com.centaury.cataloguemovie.data;

import androidx.lifecycle.LiveData;

import com.centaury.cataloguemovie.data.remote.detail.movie.DetailMovieResponse;
import com.centaury.cataloguemovie.data.remote.detail.tvshow.DetailTVShowResponse;
import com.centaury.cataloguemovie.data.remote.genre.GenresItem;
import com.centaury.cataloguemovie.data.remote.movie.MovieResultsItem;
import com.centaury.cataloguemovie.data.remote.tvshow.TVShowResultsItem;

import java.util.List;

/**
 * Created by Centaury on 10/25/2019.
 */
public interface CatalogueDataSource {

    LiveData<List<MovieResultsItem>> getMovies(String language);

    LiveData<DetailMovieResponse> getDetailMovie(String movieId, String language);

    LiveData<List<GenresItem>> getGenreMovie(String language);

    LiveData<List<TVShowResultsItem>> getTVShows(String language);

    LiveData<DetailTVShowResponse> getDetailTVShow(String tvshowId, String language);

    LiveData<List<GenresItem>> getGenreTVShow(String language);
}
