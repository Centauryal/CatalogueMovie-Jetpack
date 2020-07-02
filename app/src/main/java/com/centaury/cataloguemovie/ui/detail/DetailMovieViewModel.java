package com.centaury.cataloguemovie.ui.detail;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.centaury.cataloguemovie.data.CatalogueRepository;
import com.centaury.cataloguemovie.data.local.entity.MovieEntity;
import com.centaury.cataloguemovie.data.local.entity.TVShowEntity;

import java.util.concurrent.ExecutionException;

import javax.inject.Inject;

/**
 * Created by Centaury on 10/7/2019.
 */
public class DetailMovieViewModel extends ViewModel {

    private CatalogueRepository catalogueRepository;
    private String movieId;
    private String tvshowId;

    @Inject
    public DetailMovieViewModel(CatalogueRepository catalogueRepository) {
        this.catalogueRepository = catalogueRepository;
    }

    LiveData<DetailMovieResponse> getDetailMovie(String language) {
        return catalogueRepository.getDetailMovie(movieId, language);
    }

    LiveData<DetailTVShowResponse> getDetailTVShow(String language) {
        return catalogueRepository.getDetailTVShow(tvshowId, language);
    }

    public MovieEntity getDetailFavMovie(int id) throws ExecutionException, InterruptedException {
        return catalogueRepository.getDetailFavMovie(id);
    }

    public TVShowEntity getDetailFavTVShow(int id) throws ExecutionException, InterruptedException {
        return catalogueRepository.getDetailFavTVShow(id);
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public void setTvshowId(String tvshowId) {
        this.tvshowId = tvshowId;
    }

    void insertFavoriteMovie(MovieEntity movieEntity) {
        catalogueRepository.insertFavMovie(movieEntity);
    }

    void deleteFavoriteMovie(MovieEntity movieEntity) {
        catalogueRepository.deleteFavMovie(movieEntity);
    }

    void insertFavoriteTVShow(TVShowEntity tvShowEntity) {
        catalogueRepository.insertFavTVShow(tvShowEntity);
    }

    void deleteFavoriteTVShow(TVShowEntity tvShowEntity) {
        catalogueRepository.deleteFavTVShow(tvShowEntity);
    }
}
