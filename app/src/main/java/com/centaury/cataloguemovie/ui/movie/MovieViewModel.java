package com.centaury.cataloguemovie.ui.movie;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.centaury.cataloguemovie.data.CatalogueRepository;
import com.centaury.cataloguemovie.data.remote.genre.GenresItem;
import com.centaury.cataloguemovie.data.remote.movie.MovieResultsItem;

import java.util.List;

/**
 * Created by Centaury on 10/7/2019.
 */
public class MovieViewModel extends ViewModel {

    private CatalogueRepository catalogueRepository;

    public MovieViewModel(CatalogueRepository catalogueRepository) {
        this.catalogueRepository = catalogueRepository;
    }

    LiveData<List<MovieResultsItem>> getMovies(String language) {
        return catalogueRepository.getMovies(language);
    }

    LiveData<List<GenresItem>> getGenreMovie(String language) {
        return catalogueRepository.getGenreMovie(language);
    }
}
