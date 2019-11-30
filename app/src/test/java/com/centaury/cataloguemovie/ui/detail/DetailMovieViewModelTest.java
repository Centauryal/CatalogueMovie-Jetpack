package com.centaury.cataloguemovie.ui.detail;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.centaury.cataloguemovie.data.CatalogueRepository;
import com.centaury.cataloguemovie.data.remote.detail.movie.DetailMovieResponse;
import com.centaury.cataloguemovie.data.remote.detail.tvshow.DetailTVShowResponse;
import com.centaury.cataloguemovie.utils.FakeDataDummy;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.Locale;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Centaury on 10/7/2019.
 */
public class DetailMovieViewModelTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private DetailMovieViewModel detailMovieViewModel;
    private CatalogueRepository catalogueRepository = mock(CatalogueRepository.class);
    private DetailMovieResponse detailMovieResponse = FakeDataDummy.generateDummyResponseDetailMovies().get(0);
    private DetailTVShowResponse detailTVShowResponse = FakeDataDummy.generateDummyResponseDetailTVShows().get(0);
    private String movieId = String.valueOf(detailMovieResponse.getId());
    private String tvshowId = String.valueOf(detailTVShowResponse.getId());
    private String language = Locale.getDefault().toLanguageTag();

    @Before
    public void setUp() {
        detailMovieViewModel = new DetailMovieViewModel(catalogueRepository);
        detailMovieViewModel.setMovieId(movieId);
        detailMovieViewModel.setTvshowId(tvshowId);
    }

    @Test
    public void getMovie() {

        MutableLiveData<DetailMovieResponse> data = new MutableLiveData<>();
        data.setValue(detailMovieResponse);

        when(catalogueRepository.getDetailMovie(movieId, language)).thenReturn(data);
        Observer<DetailMovieResponse> observer = mock(Observer.class);
        detailMovieViewModel.getDetailMovie(language).observeForever(observer);
        verify(observer).onChanged(detailMovieResponse);
    }

    @Test
    public void getTVShow() {

        MutableLiveData<DetailTVShowResponse> data = new MutableLiveData<>();
        data.setValue(detailTVShowResponse);

        when(catalogueRepository.getDetailTVShow(tvshowId, language)).thenReturn(data);
        Observer<DetailTVShowResponse> observer = mock(Observer.class);
        detailMovieViewModel.getDetailTVShow(language).observeForever(observer);
        verify(observer).onChanged(detailTVShowResponse);
    }
}