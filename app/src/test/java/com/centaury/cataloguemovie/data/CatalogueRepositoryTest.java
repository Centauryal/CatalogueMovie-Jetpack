package com.centaury.cataloguemovie.data;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import com.centaury.cataloguemovie.data.remote.RemoteRepository;
import com.centaury.cataloguemovie.data.remote.detail.movie.DetailMovieResponse;
import com.centaury.cataloguemovie.data.remote.detail.tvshow.DetailTVShowResponse;
import com.centaury.cataloguemovie.data.remote.genre.GenresItem;
import com.centaury.cataloguemovie.data.remote.movie.MovieResultsItem;
import com.centaury.cataloguemovie.data.remote.tvshow.TVShowResultsItem;
import com.centaury.cataloguemovie.utils.FakeDataDummy;
import com.centaury.cataloguemovie.utils.LiveDataTestUtil;

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

/**
 * Created by Centaury on 10/29/2019.
 */
public class CatalogueRepositoryTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private RemoteRepository remoteRepository = mock(RemoteRepository.class);
    private FakeCatalogueRepository fakeCatalogueRepository = new FakeCatalogueRepository(remoteRepository);

    private List<MovieResultsItem> movieResultsItems = FakeDataDummy.generateDummyMovies();
    private List<GenresItem> genreMovieList = FakeDataDummy.generateDummyGenreMovie();
    private String movieId = String.valueOf(movieResultsItems.get(0).getId());
    private List<TVShowResultsItem> tvShowResultsItems = FakeDataDummy.generateDummyTVShows();
    private List<GenresItem> genreTVShowList = FakeDataDummy.generateDummyGenreTVShow();
    private String tvshowId = String.valueOf(tvShowResultsItems.get(0).getId());
    private DetailMovieResponse detailMovieResponse = FakeDataDummy.generateDummyDetailMovies().get(0);
    private DetailTVShowResponse detailTVShowResponse = FakeDataDummy.generateDummyDetailTVShows().get(0);
    private String language = Locale.getDefault().toLanguageTag();

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void getAllMovies() {
        doAnswer(invocation -> {
            ((RemoteRepository.LoadMoviesCallback) invocation.getArguments()[1])
                    .onResponse(movieResultsItems);
            return null;
        }).when(remoteRepository).getMovies(eq(language), any(RemoteRepository.LoadMoviesCallback.class));

        List<MovieResultsItem> resultsItems = LiveDataTestUtil.getValue(fakeCatalogueRepository.getMovies(language));

        verify(remoteRepository, times(1)).getMovies(eq(language), any(RemoteRepository.LoadMoviesCallback.class));
        assertNotNull(resultsItems);
        assertEquals(movieResultsItems.size(), resultsItems.size());
    }

    @Test
    public void getAllTVShows() {
        doAnswer(invocation -> {
            ((RemoteRepository.LoadTVShowsCallback) invocation.getArguments()[1])
                    .onResponse(tvShowResultsItems);
            return null;
        }).when(remoteRepository).getTVShows(eq(language), any(RemoteRepository.LoadTVShowsCallback.class));

        List<TVShowResultsItem> resultsItems = LiveDataTestUtil.getValue(fakeCatalogueRepository.getTVShows(language));

        verify(remoteRepository, times(1)).getTVShows(eq(language), any(RemoteRepository.LoadTVShowsCallback.class));
        assertNotNull(resultsItems);
        assertEquals(tvShowResultsItems.size(), resultsItems.size());
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
}