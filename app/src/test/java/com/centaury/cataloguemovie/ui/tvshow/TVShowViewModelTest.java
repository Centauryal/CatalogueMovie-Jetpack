package com.centaury.cataloguemovie.ui.tvshow;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.paging.PagedList;

import com.centaury.cataloguemovie.data.CatalogueRepository;
import com.centaury.cataloguemovie.utils.FakeDataDummy;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.List;
import java.util.Locale;

import static org.junit.Assert.assertNotNull;
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
        LiveData<PagedList<TVShowResultsItem>> resultsItems = tvShowViewModel.getTVShows();
        assertNotNull(resultsItems);
    }

    @Test
    public void getGenre() {
        List<GenresItem> dummyGenre = FakeDataDummy.generateDummyResponseGenreTVShow();

        MutableLiveData<List<GenresItem>> genreList = new MutableLiveData<>();
        genreList.setValue(dummyGenre);

        when(catalogueRepository.getGenreTVShow(language)).thenReturn(genreList);
        Observer<List<GenresItem>> observer = mock(Observer.class);

        tvShowViewModel.getGenreTVShow(language).observeForever(observer);
        verify(observer).onChanged(dummyGenre);
    }
}