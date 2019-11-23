package com.centaury.cataloguemovie.ui.tvshow;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.centaury.cataloguemovie.data.CatalogueRepository;
import com.centaury.cataloguemovie.data.local.entity.GenreTVShowEntity;
import com.centaury.cataloguemovie.data.local.entity.TVShowEntity;
import com.centaury.cataloguemovie.vo.Resource;

import java.util.List;

/**
 * Created by Centaury on 10/7/2019.
 */
public class TVShowViewModel extends ViewModel {

    private CatalogueRepository catalogueRepository;

    public TVShowViewModel(CatalogueRepository catalogueRepository) {
        this.catalogueRepository = catalogueRepository;
    }

    LiveData<Resource<List<TVShowEntity>>> getTVShows(String language) {
        return catalogueRepository.getTVShows(language);
    }

    LiveData<Resource<List<GenreTVShowEntity>>> getGenreTVShow(String language) {
        return catalogueRepository.getGenreTVShow(language);
    }
}
