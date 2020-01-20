package com.centaury.cataloguemovie.ui.favorite.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.PagedList;

import com.centaury.cataloguemovie.data.CatalogueRepository;
import com.centaury.cataloguemovie.data.local.entity.MovieEntity;

import java.util.concurrent.ExecutionException;

import javax.inject.Inject;

/**
 * Created by Centaury on 11/23/2019.
 */
public class FavoriteMovieViewModel extends ViewModel {

    private CatalogueRepository catalogueRepository;

    @Inject
    public FavoriteMovieViewModel(CatalogueRepository catalogueRepository) {
        this.catalogueRepository = catalogueRepository;
    }

    public LiveData<PagedList<MovieEntity>> getFavoriteMovie() {
        return catalogueRepository.getFavoriteMovies();
    }

    public MovieEntity getDetailFavMovie(int id) throws ExecutionException, InterruptedException {
        return catalogueRepository.getDetailFavMovie(id);
    }

    public void deleteFavoriteMovie(MovieEntity movieEntity) {
        catalogueRepository.deleteFavMovie(movieEntity);
    }
}
