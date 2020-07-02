package com.centaury.cataloguemovie.ui.movie;

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
public class MovieViewModelTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private MovieViewModel movieViewModel;
    private CatalogueRepository catalogueRepository = mock(CatalogueRepository.class);
    private String language = Locale.getDefault().toLanguageTag();

    @Before
    public void setUp() {
        movieViewModel = new MovieViewModel(catalogueRepository);
    }

    @Test
    public void getMovies() {
        LiveData<PagedList<MovieResultsItem>> resultsItems = movieViewModel.getMovies();
        assertNotNull(resultsItems);
    }

    @Test
    public void getGenre() {
        List<GenresItem> dummyGenre = FakeDataDummy.generateDummyResponseGenreMovie();

        MutableLiveData<List<GenresItem>> genreList = new MutableLiveData<>();
        genreList.setValue(dummyGenre);

        when(catalogueRepository.getGenreMovie(language)).thenReturn(genreList);
        Observer<List<GenresItem>> observer = mock(Observer.class);

        movieViewModel.getGenreMovie(language).observeForever(observer);
        verify(observer).onChanged(dummyGenre);
    }
}