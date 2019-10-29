package com.centaury.cataloguemovie.ui.tvshow;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.centaury.cataloguemovie.data.CatalogueRepository;
import com.centaury.cataloguemovie.data.remote.genre.GenresItem;
import com.centaury.cataloguemovie.data.remote.tvshow.TVShowResultsItem;

import java.util.List;

/**
 * Created by Centaury on 10/7/2019.
 */
public class TVShowViewModel extends ViewModel {

    private CatalogueRepository catalogueRepository;

    public TVShowViewModel(CatalogueRepository catalogueRepository) {
        this.catalogueRepository = catalogueRepository;
    }

    LiveData<List<TVShowResultsItem>> getTVShows(String language) {
        return catalogueRepository.getTVShows(language);
    }

    LiveData<List<GenresItem>> getGenreTVShow(String language) {
        return catalogueRepository.getGenreTVShow(language);
    }
}
