package com.centaury.cataloguemovie.ui.favorite.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.PagedList;

import com.centaury.cataloguemovie.data.CatalogueRepository;
import com.centaury.cataloguemovie.data.local.entity.GenreTVShowEntity;
import com.centaury.cataloguemovie.data.local.entity.TVShowEntity;
import com.centaury.cataloguemovie.vo.Resource;

import java.util.List;

/**
 * Created by Centaury on 11/23/2019.
 */
public class FavoriteTVShowViewModel extends ViewModel {

    private CatalogueRepository catalogueRepository;

    public FavoriteTVShowViewModel(CatalogueRepository catalogueRepository) {
        this.catalogueRepository = catalogueRepository;
    }

    public LiveData<Resource<PagedList<TVShowEntity>>> getFavoriteTVShow() {
        return catalogueRepository.getFavoritedTVShows();
    }

    public LiveData<Resource<List<GenreTVShowEntity>>> getGenreTVShow(String language) {
        return catalogueRepository.getGenreTVShow(language);
    }
}
