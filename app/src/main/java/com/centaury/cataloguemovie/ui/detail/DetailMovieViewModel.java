package com.centaury.cataloguemovie.ui.detail;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.centaury.cataloguemovie.data.CatalogueRepository;
import com.centaury.cataloguemovie.data.remote.detail.movie.DetailMovieResponse;
import com.centaury.cataloguemovie.data.remote.detail.tvshow.DetailTVShowResponse;

/**
 * Created by Centaury on 10/7/2019.
 */
public class DetailMovieViewModel extends ViewModel {

    private CatalogueRepository catalogueRepository;
    private String movieId;
    private String tvshowId;

    public DetailMovieViewModel(CatalogueRepository catalogueRepository) {
        this.catalogueRepository = catalogueRepository;
    }

    LiveData<DetailMovieResponse> getDetailMovie(String language) {
        return catalogueRepository.getDetailMovie(movieId, language);
    }

    LiveData<DetailTVShowResponse> getDetailTVShow(String language) {
        return catalogueRepository.getDetailTVShow(tvshowId, language);
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public void setTvshowId(String tvshowId) {
        this.tvshowId = tvshowId;
    }
}
