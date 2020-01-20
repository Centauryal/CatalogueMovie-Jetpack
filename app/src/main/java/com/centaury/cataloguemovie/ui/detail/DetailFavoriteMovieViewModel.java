package com.centaury.cataloguemovie.ui.detail;

import androidx.lifecycle.ViewModel;

import com.centaury.cataloguemovie.data.CatalogueRepository;
import com.centaury.cataloguemovie.data.local.entity.MovieEntity;
import com.centaury.cataloguemovie.data.local.entity.TVShowEntity;

import java.util.concurrent.ExecutionException;

import javax.inject.Inject;

/**
 * Created by Centaury on 11/27/2019.
 */
public class DetailFavoriteMovieViewModel extends ViewModel {

    private CatalogueRepository catalogueRepository;

    @Inject
    public DetailFavoriteMovieViewModel(CatalogueRepository catalogueRepository) {
        this.catalogueRepository = catalogueRepository;
    }

    public MovieEntity getDetailFavMovie(int id) throws ExecutionException, InterruptedException {
        return catalogueRepository.getDetailFavMovie(id);
    }

    public TVShowEntity getDetailFavTVShow(int id) throws ExecutionException, InterruptedException {
        return catalogueRepository.getDetailFavTVShow(id);
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
