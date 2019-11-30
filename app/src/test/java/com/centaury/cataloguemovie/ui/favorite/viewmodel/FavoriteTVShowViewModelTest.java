package com.centaury.cataloguemovie.ui.favorite.viewmodel;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.paging.PagedList;

import com.centaury.cataloguemovie.data.CatalogueRepository;
import com.centaury.cataloguemovie.data.local.entity.TVShowEntity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Centaury on 11/26/2019.
 */
public class FavoriteTVShowViewModelTest {
    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private FavoriteTVShowViewModel favoriteTVShowViewModel;
    private CatalogueRepository catalogueRepository = mock(CatalogueRepository.class);

    @Before
    public void setUp() {
        favoriteTVShowViewModel = new FavoriteTVShowViewModel(catalogueRepository);
    }

    @Test
    public void getTVShows() {

        MutableLiveData<PagedList<TVShowEntity>> dummyFavorites = new MutableLiveData<>();
        PagedList<TVShowEntity> pagedList = mock(PagedList.class);

        dummyFavorites.setValue(pagedList);

        when(catalogueRepository.getFavoriteTVShows()).thenReturn(dummyFavorites);

        Observer<PagedList<TVShowEntity>> observer = mock(Observer.class);

        favoriteTVShowViewModel.getFavoriteTVShow().observeForever(observer);

        verify(observer).onChanged(pagedList);
    }
}