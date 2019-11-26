package com.centaury.cataloguemovie.ui.favorite.viewmodel;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.centaury.cataloguemovie.data.CatalogueRepository;
import com.centaury.cataloguemovie.data.local.entity.GenreTVShowEntity;
import com.centaury.cataloguemovie.data.local.entity.TVShowEntity;
import com.centaury.cataloguemovie.utils.FakeDataDummy;
import com.centaury.cataloguemovie.vo.Resource;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.List;
import java.util.Locale;

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
    private String language = Locale.getDefault().toLanguageTag();

    @Before
    public void setUp() {
        favoriteTVShowViewModel = new FavoriteTVShowViewModel(catalogueRepository);
    }

    @Test
    public void getTVShows() {

        Resource<List<TVShowEntity>> resource = Resource.success(FakeDataDummy.generateDummyTVShows());

        MutableLiveData<Resource<List<TVShowEntity>>> dummyTVShow = new MutableLiveData<>();
        dummyTVShow.setValue(resource);

        when(catalogueRepository.getFavoritedTVShows()).thenReturn(dummyTVShow);
        Observer<Resource<List<TVShowEntity>>> observer = mock(Observer.class);

        favoriteTVShowViewModel.getFavoriteTVShow().observeForever(observer);
        verify(observer).onChanged(resource);
    }

    @Test
    public void getGenre() {
        Resource<List<GenreTVShowEntity>> resource = Resource.success(FakeDataDummy.generateDummyGenreTVShow());

        MutableLiveData<Resource<List<GenreTVShowEntity>>> dummyGenre = new MutableLiveData<>();
        dummyGenre.setValue(resource);

        when(catalogueRepository.getGenreTVShow(language)).thenReturn(dummyGenre);
        Observer<Resource<List<GenreTVShowEntity>>> observer = mock(Observer.class);

        favoriteTVShowViewModel.getGenreTVShow(language).observeForever(observer);
        verify(observer).onChanged(resource);
    }
}