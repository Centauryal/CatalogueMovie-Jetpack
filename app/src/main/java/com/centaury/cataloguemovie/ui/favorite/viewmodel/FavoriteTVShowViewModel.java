package com.centaury.cataloguemovie.ui.favorite.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.PagedList;

import com.centaury.cataloguemovie.data.CatalogueRepository;
import com.centaury.cataloguemovie.data.local.entity.TVShowEntity;

import java.util.concurrent.ExecutionException;

import javax.inject.Inject;

/**
 * Created by Centaury on 11/23/2019.
 */
public class FavoriteTVShowViewModel extends ViewModel {

    private CatalogueRepository catalogueRepository;

    @Inject
    public FavoriteTVShowViewModel(CatalogueRepository catalogueRepository) {
        this.catalogueRepository = catalogueRepository;
    }

    public LiveData<PagedList<TVShowEntity>> getFavoriteTVShow() {
        return catalogueRepository.getFavoriteTVShows();
    }

    public TVShowEntity getDetailFavTVShow(int id) throws ExecutionException, InterruptedException {
        return catalogueRepository.getDetailFavTVShow(id);
    }

    public void deleteFavoriteTVShow(TVShowEntity tvShowEntity) {
        catalogueRepository.deleteFavTVShow(tvShowEntity);
    }
}
