package com.centaury.cataloguemovie.ui.movie;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.centaury.cataloguemovie.data.CatalogueRepository;
import com.centaury.cataloguemovie.data.local.entity.GenreMovieEntity;
import com.centaury.cataloguemovie.data.local.entity.MovieEntity;
import com.centaury.cataloguemovie.vo.Resource;

import java.util.List;

/**
 * Created by Centaury on 10/7/2019.
 */
public class MovieViewModel extends ViewModel {

    private CatalogueRepository catalogueRepository;

    public MovieViewModel(CatalogueRepository catalogueRepository) {
        this.catalogueRepository = catalogueRepository;
    }

    LiveData<Resource<List<MovieEntity>>> getMovies(String language) {
        return catalogueRepository.getMovies(language);
    }

    LiveData<Resource<List<GenreMovieEntity>>> getGenreMovie(String language) {
        return catalogueRepository.getGenreMovie(language);
    }
}
