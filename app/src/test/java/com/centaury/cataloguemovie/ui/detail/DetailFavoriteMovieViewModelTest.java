package com.centaury.cataloguemovie.ui.detail;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import com.centaury.cataloguemovie.data.CatalogueRepository;
import com.centaury.cataloguemovie.data.local.entity.MovieEntity;
import com.centaury.cataloguemovie.data.local.entity.TVShowEntity;
import com.centaury.cataloguemovie.utils.FakeDataDummy;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;

/**
 * Created by Centaury on 11/28/2019.
 */
public class DetailFavoriteMovieViewModelTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private DetailFavoriteMovieViewModel detailFavoriteMovieViewModel;
    private CatalogueRepository catalogueRepository = mock(CatalogueRepository.class);
    private MovieEntity movieEntity = FakeDataDummy.generateDummyMovies().get(0);
    private TVShowEntity tvShowEntity = FakeDataDummy.generateDummyTVShows().get(0);

    @Before
    public void setUp() {
        detailFavoriteMovieViewModel = new DetailFavoriteMovieViewModel(catalogueRepository);
    }

    @Test
    public void getMovie() {
        detailFavoriteMovieViewModel.insertFavoriteMovie(movieEntity);
        assertNotNull(movieEntity);

    }

    @Test
    public void getTVShow() {
        detailFavoriteMovieViewModel.insertFavoriteTVShow(tvShowEntity);
        assertNotNull(tvShowEntity);
    }
}