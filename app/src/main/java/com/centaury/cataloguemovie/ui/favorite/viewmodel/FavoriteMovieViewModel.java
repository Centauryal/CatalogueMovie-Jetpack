package com.centaury.cataloguemovie.ui.favorite.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.PagedList;

import com.centaury.cataloguemovie.data.CatalogueRepository;
import com.centaury.cataloguemovie.data.local.entity.GenreMovieEntity;
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

    public LiveData<Resource<PagedList<MovieEntity>>> getFavoriteMovie() {
        return catalogueRepository.getFavoritedMovies();
    }

    public LiveData<Resource<List<GenreMovieEntity>>> getGenreMovie(String language) {
        return catalogueRepository.getGenreMovie(language);
    }
}
