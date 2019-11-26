package com.centaury.cataloguemovie.ui.favorite.viewmodel;

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
 * Created by Centaury on 11/26/2019.
 */
public class FavoriteMovieViewModelTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private FavoriteMovieViewModel favoriteMovieViewModel;
    private CatalogueRepository catalogueRepository = mock(CatalogueRepository.class);
    private String language = Locale.getDefault().toLanguageTag();

    @Before
    public void setUp() {
        favoriteMovieViewModel = new FavoriteMovieViewModel(catalogueRepository);
    }

    @Test
    public void getFavoriteMovies() {

        Resource<List<MovieEntity>> resource = Resource.success(FakeDataDummy.generateDummyMovies());

        MutableLiveData<Resource<List<MovieEntity>>> dummyMovie = new MutableLiveData<>();
        dummyMovie.setValue(resource);

        when(catalogueRepository.getFavoritedMovies()).thenReturn(dummyMovie);
        Observer<Resource<List<MovieEntity>>> observer = mock(Observer.class);

        favoriteMovieViewModel.getFavoriteMovie().observeForever(observer);
        verify(observer).onChanged(resource);
    }

    @Test
    public void getGenreFavoriteMovie() {

        Resource<List<GenreMovieEntity>> resource = Resource.success(FakeDataDummy.generateDummyGenreMovie());

        MutableLiveData<Resource<List<GenreMovieEntity>>> dummyGenre = new MutableLiveData<>();
        dummyGenre.setValue(resource);

        when(catalogueRepository.getGenreMovie(language)).thenReturn(dummyGenre);
        Observer<Resource<List<GenreMovieEntity>>> observer = mock(Observer.class);

        favoriteMovieViewModel.getGenreMovie(language).observeForever(observer);
        verify(observer).onChanged(resource);
    }
}