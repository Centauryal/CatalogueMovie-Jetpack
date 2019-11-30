package com.centaury.cataloguemovie.ui.favorite.viewmodel;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.paging.PagedList;

import com.centaury.cataloguemovie.data.CatalogueRepository;
import com.centaury.cataloguemovie.data.local.entity.MovieEntity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Centaury on 11/26/2019.
 */
public class FavoriteMovieViewModelTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private FavoriteMovieViewModel favoriteMovieViewModel;
    private CatalogueRepository catalogueRepository = mock(CatalogueRepository.class);

    @Before
    public void setUp() {
        favoriteMovieViewModel = new FavoriteMovieViewModel(catalogueRepository);
    }

    @Test
    public void getFavoriteMovies() {

        MutableLiveData<PagedList<MovieEntity>> dummyFavorites = new MutableLiveData<>();
        PagedList<MovieEntity> pagedList = mock(PagedList.class);

        dummyFavorites.setValue(pagedList);

        when(catalogueRepository.getFavoriteMovies()).thenReturn(dummyFavorites);

        Observer<PagedList<MovieEntity>> observer = mock(Observer.class);

        favoriteMovieViewModel.getFavoriteMovie().observeForever(observer);

        verify(observer).onChanged(pagedList);
    }
}