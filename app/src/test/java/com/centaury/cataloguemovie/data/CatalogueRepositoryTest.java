package com.centaury.cataloguemovie.data;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.paging.DataSource;
import androidx.paging.PagedList;

import com.centaury.cataloguemovie.data.local.LocalRepository;
import com.centaury.cataloguemovie.data.local.entity.MovieEntity;
import com.centaury.cataloguemovie.data.local.entity.TVShowEntity;
import com.centaury.cataloguemovie.data.remote.RemoteRepository;
import com.centaury.cataloguemovie.utils.FakeDataDummy;
import com.centaury.cataloguemovie.utils.LiveDataTestUtil;
import com.centaury.cataloguemovie.utils.PagedListUtil;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.List;
import java.util.Locale;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Centaury on 10/29/2019.
 */
public class CatalogueRepositoryTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private LocalRepository localRepository = mock(LocalRepository.class);
    private RemoteRepository remoteRepository = mock(RemoteRepository.class);
    private FakeCatalogueRepository fakeCatalogueRepository = new FakeCatalogueRepository(localRepository, remoteRepository);

    private List<MovieResultsItem> movieResultsItems = FakeDataDummy.generateDummyResponseMovies();
    private List<GenresItem> genreMovieList = FakeDataDummy.generateDummyResponseGenreMovie();
    private String movieId = String.valueOf(movieResultsItems.get(0).getId());
    private List<TVShowResultsItem> tvShowResultsItems = FakeDataDummy.generateDummyResponseTVShows();
    private List<GenresItem> genreTVShowList = FakeDataDummy.generateDummyResponseGenreTVShow();
    private String tvshowId = String.valueOf(tvShowResultsItems.get(0).getId());
    private DetailMovieResponse detailMovieResponse = FakeDataDummy.generateDummyResponseDetailMovies().get(0);
    private DetailTVShowResponse detailTVShowResponse = FakeDataDummy.generateDummyResponseDetailTVShows().get(0);
    private List<MovieEntity> movieEntities = FakeDataDummy.generateDummyMovies();
    private List<TVShowEntity> tvShowEntities = FakeDataDummy.generateDummyTVShows();
    private String language = Locale.getDefault().toLanguageTag();

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void getGenreMovies() {
        doAnswer(invocation -> {
            ((RemoteRepository.GetGenreMovieCallback) invocation.getArguments()[1])
                    .onResponse(genreMovieList);
            return null;
        }).when(remoteRepository).getGenreMovie(eq(language), any(RemoteRepository.GetGenreMovieCallback.class));

        List<GenresItem> resultsItems = LiveDataTestUtil.getValue(fakeCatalogueRepository.getGenreMovie(language));

        verify(remoteRepository, times(1)).getGenreMovie(eq(language), any(RemoteRepository.GetGenreMovieCallback.class));
        assertNotNull(resultsItems);
        assertEquals(genreMovieList.size(), resultsItems.size());
    }

    @Test
    public void getGenreTVShows() {
        doAnswer(invocation -> {
            ((RemoteRepository.GetGenreTVShowCallback) invocation.getArguments()[1])
                    .onResponse(genreTVShowList);
            return null;
        }).when(remoteRepository).getGenreTVShow(eq(language), any(RemoteRepository.GetGenreTVShowCallback.class));

        List<GenresItem> resultsItems = LiveDataTestUtil.getValue(fakeCatalogueRepository.getGenreTVShow(language));

        verify(remoteRepository, times(1)).getGenreTVShow(eq(language), any(RemoteRepository.GetGenreTVShowCallback.class));
        assertNotNull(resultsItems);
        assertEquals(genreTVShowList.size(), resultsItems.size());
    }

    @Test
    public void getDetailMovie() {
        doAnswer(invocation -> {
            ((RemoteRepository.GetMovieDetailCallback) invocation.getArguments()[2])
                    .onResponse(detailMovieResponse);
            return null;
        }).when(remoteRepository).getMovieDetail(eq(movieId), eq(language), any(RemoteRepository.GetMovieDetailCallback.class));

        DetailMovieResponse resultDetail = LiveDataTestUtil.getValue(fakeCatalogueRepository.getDetailMovie(movieId, language));

        verify(remoteRepository, times(1))
                .getMovieDetail(eq(movieId), eq(language), any(RemoteRepository.GetMovieDetailCallback.class));

        assertNotNull(resultDetail);
        assertEquals(detailMovieResponse.getTitle(), resultDetail.getTitle());
    }

    @Test
    public void getDetailTVShow() {
        doAnswer(invocation -> {
            ((RemoteRepository.GetTVShowDetailCallback) invocation.getArguments()[2])
                    .onResponse(detailTVShowResponse);
            return null;
        }).when(remoteRepository).getTVShowDetail(eq(tvshowId), eq(language), any(RemoteRepository.GetTVShowDetailCallback.class));

        DetailTVShowResponse resultDetail = LiveDataTestUtil.getValue(fakeCatalogueRepository.getDetailTVShow(tvshowId, language));

        verify(remoteRepository, times(1))
                .getTVShowDetail(eq(tvshowId), eq(language), any(RemoteRepository.GetTVShowDetailCallback.class));

        assertNotNull(resultDetail);
        assertEquals(detailTVShowResponse.getName(), resultDetail.getName());
    }

    @Test
    public void getFavoriteMovie() {

        DataSource.Factory<Integer, MovieEntity> dataSource = mock(DataSource.Factory.class);

        when(localRepository.getAllFavMovies()).thenReturn(dataSource);
        fakeCatalogueRepository.getFavoriteMovies();
        PagedList<MovieEntity> result = PagedListUtil.mockPagedList(movieEntities);

        verify(localRepository).getAllFavMovies();
        assertNotNull(result);
        assertEquals(movieEntities.size(), result.size());
    }

    @Test
    public void getFavoriteTVShow() {
        DataSource.Factory<Integer, TVShowEntity> dataSource = mock(DataSource.Factory.class);

        when(localRepository.getAllFavTVShows()).thenReturn(dataSource);
        fakeCatalogueRepository.getFavoriteTVShows();
        PagedList<TVShowEntity> result = PagedListUtil.mockPagedList(tvShowEntities);

        verify(localRepository).getAllFavTVShows();
        assertNotNull(result);
        assertEquals(tvShowEntities.size(), result.size());
    }
}