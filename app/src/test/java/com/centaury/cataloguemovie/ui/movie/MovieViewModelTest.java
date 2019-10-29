package com.centaury.cataloguemovie.ui.movie;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.centaury.cataloguemovie.data.CatalogueRepository;
import com.centaury.cataloguemovie.data.remote.genre.GenresItem;
import com.centaury.cataloguemovie.data.remote.movie.MovieResultsItem;
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

        List<MovieResultsItem> dummyMovie = FakeDataDummy.generateDummyMovies();

        MutableLiveData<List<MovieResultsItem>> movieList = new MutableLiveData<>();
        movieList.setValue(dummyMovie);

        when(catalogueRepository.getMovies(language)).thenReturn(movieList);
        Observer<List<MovieResultsItem>> observer = mock(Observer.class);

        movieViewModel.getMovies(language).observeForever(observer);
        verify(observer).onChanged(dummyMovie);
    }

    @Test
    public void getGenre() {
        List<GenresItem> dummyGenre = FakeDataDummy.generateDummyGenreMovie();

        MutableLiveData<List<GenresItem>> genreList = new MutableLiveData<>();
        genreList.setValue(dummyGenre);

        when(catalogueRepository.getGenreMovie(language)).thenReturn(genreList);
        Observer<List<GenresItem>> observer = mock(Observer.class);

        movieViewModel.getGenreMovie(language).observeForever(observer);
        verify(observer).onChanged(dummyGenre);
    }
}