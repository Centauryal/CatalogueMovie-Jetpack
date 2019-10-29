package com.centaury.cataloguemovie.ui.tvshow;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.centaury.cataloguemovie.data.CatalogueRepository;
import com.centaury.cataloguemovie.data.remote.genre.GenresItem;
import com.centaury.cataloguemovie.data.remote.tvshow.TVShowResultsItem;
import com.centaury.cataloguemovie.utils.FakeDataDummy;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.List;
import java.util.Locale;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Centaury on 10/7/2019.
 */
public class TVShowViewModelTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private TVShowViewModel tvShowViewModel;
    private CatalogueRepository catalogueRepository = mock(CatalogueRepository.class);
    private String language = Locale.getDefault().toLanguageTag();

    @Before
    public void setUp() {
        tvShowViewModel = new TVShowViewModel(catalogueRepository);
    }

    @Test
    public void getTVShows() {

        List<TVShowResultsItem> dummyTVShow = FakeDataDummy.generateDummyTVShows();

        MutableLiveData<List<TVShowResultsItem>> tvshowList = new MutableLiveData<>();
        tvshowList.setValue(dummyTVShow);

        when(catalogueRepository.getTVShows(language)).thenReturn(tvshowList);
        Observer<List<TVShowResultsItem>> observer = mock(Observer.class);

        tvShowViewModel.getTVShows(language).observeForever(observer);
        verify(observer).onChanged(dummyTVShow);
    }

    @Test
    public void getGenre() {
        List<GenresItem> dummyGenre = FakeDataDummy.generateDummyGenreTVShow();

        MutableLiveData<List<GenresItem>> genreList = new MutableLiveData<>();
        genreList.setValue(dummyGenre);

        when(catalogueRepository.getGenreTVShow(language)).thenReturn(genreList);
        Observer<List<GenresItem>> observer = mock(Observer.class);

        tvShowViewModel.getGenreTVShow(language).observeForever(observer);
        verify(observer).onChanged(dummyGenre);
    }
}