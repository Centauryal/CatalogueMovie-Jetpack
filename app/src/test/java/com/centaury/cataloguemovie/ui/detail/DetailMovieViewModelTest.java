package com.centaury.cataloguemovie.ui.detail;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.centaury.cataloguemovie.data.CatalogueRepository;
import com.centaury.cataloguemovie.data.local.entity.MovieEntity;
import com.centaury.cataloguemovie.data.local.entity.TVShowEntity;
import com.centaury.cataloguemovie.utils.FakeDataDummy;
import com.centaury.cataloguemovie.vo.Resource;

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
    private MovieEntity detailMovieResponse = FakeDataDummy.generateDummyDetailMovie(false);
    private TVShowEntity detailTVShowResponse = FakeDataDummy.generateDummyDetailTVShow(false);
    private String movieId = String.valueOf(detailMovieResponse.getMovieId());
    private String tvshowId = String.valueOf(detailTVShowResponse.getTvshowId());
    private String language = Locale.getDefault().toLanguageTag();

    @Before
    public void setUp() {
        detailMovieViewModel = new DetailMovieViewModel(catalogueRepository);
        detailMovieViewModel.setMovieId(movieId);
        detailMovieViewModel.setTvshowId(tvshowId);
        detailMovieViewModel.setLanguage(language);
        detailMovieViewModel.setFavoriteMovie(detailMovieResponse, false);
        detailMovieViewModel.setFavoriteTVShow(detailTVShowResponse, false);
    }

    @Test
    public void getMovie() {

        Resource<MovieEntity> resource = Resource.success(FakeDataDummy.generateDummyDetailMovie(true));
        MutableLiveData<Resource<MovieEntity>> data = new MutableLiveData<>();
        data.setValue(resource);

        when(catalogueRepository.getDetailMovie(movieId, language)).thenReturn(data);

        Observer<Resource<MovieEntity>> observer = mock(Observer.class);
        detailMovieViewModel.getDetailMovie().observeForever(observer);
        verify(observer).onChanged(resource);
    }

    @Test
    public void getTVShow() {

        Resource<TVShowEntity> resource = Resource.success(FakeDataDummy.generateDummyDetailTVShow(true));
        MutableLiveData<Resource<TVShowEntity>> data = new MutableLiveData<>();
        data.setValue(resource);

        when(catalogueRepository.getDetailTVShow(tvshowId, language)).thenReturn(data);

        Observer<Resource<TVShowEntity>> observer = mock(Observer.class);
        detailMovieViewModel.getDetailTVShow().observeForever(observer);

        verify(observer).onChanged(resource);
    }
}