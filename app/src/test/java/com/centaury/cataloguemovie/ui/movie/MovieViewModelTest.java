package com.centaury.cataloguemovie.ui.movie;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.centaury.cataloguemovie.data.CatalogueRepository;
import com.centaury.cataloguemovie.data.local.entity.GenreMovieEntity;
import com.centaury.cataloguemovie.data.local.entity.MovieEntity;
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

        Resource<List<MovieEntity>> resource = Resource.success(FakeDataDummy.generateDummyMovies());

        MutableLiveData<Resource<List<MovieEntity>>> dummyMovie = new MutableLiveData<>();
        dummyMovie.setValue(resource);

        when(catalogueRepository.getMovies(language)).thenReturn(dummyMovie);
        Observer<Resource<List<MovieEntity>>> observer = mock(Observer.class);

        movieViewModel.getMovies(language).observeForever(observer);
        verify(observer).onChanged(resource);
    }

    @Test
    public void getGenre() {
        Resource<List<GenreMovieEntity>> resource = Resource.success(FakeDataDummy.generateDummyGenreMovie());

        MutableLiveData<Resource<List<GenreMovieEntity>>> dummyGenre = new MutableLiveData<>();
        dummyGenre.setValue(resource);

        when(catalogueRepository.getGenreMovie(language)).thenReturn(dummyGenre);
        Observer<Resource<List<GenreMovieEntity>>> observer = mock(Observer.class);

        movieViewModel.getGenreMovie(language).observeForever(observer);
        verify(observer).onChanged(resource);
    }
}