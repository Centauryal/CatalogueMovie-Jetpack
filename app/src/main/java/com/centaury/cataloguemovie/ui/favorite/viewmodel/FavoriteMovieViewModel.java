package com.centaury.cataloguemovie.ui.favorite.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.centaury.cataloguemovie.data.CatalogueRepository;
import com.centaury.cataloguemovie.data.local.entity.MovieEntity;
import com.centaury.cataloguemovie.vo.Resource;

import java.util.List;

/**
 * Created by Centaury on 11/23/2019.
 */
public class FavoriteMovieViewModel extends ViewModel {

    private CatalogueRepository catalogueRepository;

    public FavoriteMovieViewModel(CatalogueRepository catalogueRepository) {
        this.catalogueRepository = catalogueRepository;
    }

    public LiveData<Resource<List<MovieEntity>>> getFavoriteMovie() {
        return catalogueRepository.getFavoritedMovies();
    }
}
