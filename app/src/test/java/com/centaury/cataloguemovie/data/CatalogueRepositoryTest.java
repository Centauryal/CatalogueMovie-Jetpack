package com.centaury.cataloguemovie.data;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;

import com.centaury.cataloguemovie.data.local.LocalRepository;
import com.centaury.cataloguemovie.data.local.entity.GenreMovieEntity;
import com.centaury.cataloguemovie.data.local.entity.GenreTVShowEntity;
import com.centaury.cataloguemovie.data.local.entity.MovieEntity;
import com.centaury.cataloguemovie.data.local.entity.TVShowEntity;
import com.centaury.cataloguemovie.data.remote.RemoteRepository;
import com.centaury.cataloguemovie.data.remote.detail.movie.DetailMovieResponse;
import com.centaury.cataloguemovie.data.remote.detail.tvshow.DetailTVShowResponse;
import com.centaury.cataloguemovie.data.remote.genre.GenresItem;
import com.centaury.cataloguemovie.data.remote.movie.MovieResultsItem;
import com.centaury.cataloguemovie.data.remote.tvshow.TVShowResultsItem;
import com.centaury.cataloguemovie.utils.FakeDataDummy;
import com.centaury.cataloguemovie.utils.InstantAppExecutors;
import com.centaury.cataloguemovie.utils.LiveDataTestUtil;
import com.centaury.cataloguemovie.vo.Resource;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.List;
import java.util.Locale;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
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
    private InstantAppExecutors instantAppExecutors = mock(InstantAppExecutors.class);
    private FakeCatalogueRepository fakeCatalogueRepository = new FakeCatalogueRepository(localRepository, remoteRepository, instantAppExecutors);

    private List<MovieResultsItem> movieResultsItems = FakeDataDummy.generateDummyResponseMovies();
    private List<GenresItem> genreMovieList = FakeDataDummy.generateDummyResponseGenreMovie();
    private String movieId = String.valueOf(movieResultsItems.get(0).getId());
    private List<TVShowResultsItem> tvShowResultsItems = FakeDataDummy.generateDummyResponseTVShows();
    private List<GenresItem> genreTVShowList = FakeDataDummy.generateDummyResponseGenreTVShow();
    private String tvshowId = String.valueOf(tvShowResultsItems.get(0).getId());
    private DetailMovieResponse detailMovieResponse = FakeDataDummy.generateDummyResponseDetailMovies().get(0);
    private DetailTVShowResponse detailTVShowResponse = FakeDataDummy.generateDummyResponseDetailTVShows().get(0);
    private String language = Locale.getDefault().toLanguageTag();

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void getAllMovies() {

        MutableLiveData<List<MovieEntity>> dummyMovie = new MutableLiveData<>();
        dummyMovie.setValue(FakeDataDummy.generateDummyMovies());

        when(localRepository.getAllMovies()).thenReturn(dummyMovie);

        Resource<List<MovieEntity>> result = LiveDataTestUtil.getValue(fakeCatalogueRepository.getMovies(language));

        verify(localRepository).getAllMovies();
        assertNotNull(result.data);
        assertEquals(movieResultsItems.size(), result.data.size());
    }

    @Test
    public void getAllTVShows() {
        MutableLiveData<List<TVShowEntity>> dummyTVShow = new MutableLiveData<>();
        dummyTVShow.setValue(FakeDataDummy.generateDummyTVShows());

        when(localRepository.getAllTVShows()).thenReturn(dummyTVShow);

        Resource<List<TVShowEntity>> result = LiveDataTestUtil.getValue(fakeCatalogueRepository.getTVShows(language));

        verify(localRepository).getAllTVShows();
        assertNotNull(result.data);
        assertEquals(tvShowResultsItems.size(), result.data.size());
    }

    @Test
    public void getGenreMovies() {
        MutableLiveData<List<GenreMovieEntity>> dummyGenreMovie = new MutableLiveData<>();
        dummyGenreMovie.setValue(FakeDataDummy.generateDummyGenreMovie());

        when(localRepository.getAllGenresMovie()).thenReturn(dummyGenreMovie);

        Resource<List<GenreMovieEntity>> result = LiveDataTestUtil.getValue(fakeCatalogueRepository.getGenreMovie(language));

        verify(localRepository).getAllGenresMovie();
        assertNotNull(result.data);
        assertEquals(genreMovieList.size(), result.data.size());
    }

    @Test
    public void getGenreTVShows() {
        MutableLiveData<List<GenreTVShowEntity>> dummyGenreTVShow = new MutableLiveData<>();
        dummyGenreTVShow.setValue(FakeDataDummy.generateDummyGenreTVShow());

        when(localRepository.getAllGenresTVShow()).thenReturn(dummyGenreTVShow);

        Resource<List<GenreTVShowEntity>> result = LiveDataTestUtil.getValue(fakeCatalogueRepository.getGenreTVShow(language));

        verify(localRepository).getAllGenresTVShow();
        assertNotNull(result.data);
        assertEquals(genreTVShowList.size(), result.data.size());
    }

    @Test
    public void getDetailMovie() {
        MutableLiveData<MovieEntity> dummyMovie = new MutableLiveData<>();
        dummyMovie.setValue(FakeDataDummy.generateDummyMovies().get(0));

        when(localRepository.getMovieById(Integer.parseInt(movieId))).thenReturn(dummyMovie);

        Resource<MovieEntity> result = LiveDataTestUtil.getValue(fakeCatalogueRepository.getDetailMovie(movieId, language));

        verify(localRepository).getMovieById(Integer.parseInt(movieId));
        assertNotNull(result.data);
        assertNotNull(result.data.getName());
    }

    @Test
    public void getDetailTVShow() {
        MutableLiveData<TVShowEntity> dummyTVShow = new MutableLiveData<>();
        dummyTVShow.setValue(FakeDataDummy.generateDummyTVShows().get(0));

        when(localRepository.getTVShowById(Integer.parseInt(tvshowId))).thenReturn(dummyTVShow);

        Resource<TVShowEntity> result = LiveDataTestUtil.getValue(fakeCatalogueRepository.getDetailTVShow(tvshowId, language));

        verify(localRepository).getTVShowById(Integer.parseInt(tvshowId));
        assertNotNull(result.data);
        assertNotNull(result.data.getName());
    }

    @Test
    public void getFavoriteMovie() {
        MutableLiveData<List<MovieEntity>> dummyFavoriteMovie = new MutableLiveData<>();
        dummyFavoriteMovie.setValue(FakeDataDummy.generateDummyMovies());

        when(localRepository.getFavoritedMovies()).thenReturn(dummyFavoriteMovie);

        Resource<List<MovieEntity>> result = LiveDataTestUtil.getValue(fakeCatalogueRepository.getFavoritedMovies());

        verify(localRepository).getFavoritedMovies();
        assertNotNull(result.data);
        assertEquals(movieResultsItems.size(), result.data.size());
    }

    @Test
    public void getFavoriteTVShow() {
        MutableLiveData<List<TVShowEntity>> dummyFavoriteTVSshow = new MutableLiveData<>();
        dummyFavoriteTVSshow.setValue(FakeDataDummy.generateDummyTVShows());

        when(localRepository.getFavoritedTVShows()).thenReturn(dummyFavoriteTVSshow);

        Resource<List<TVShowEntity>> result = LiveDataTestUtil.getValue(fakeCatalogueRepository.getFavoritedTVShows());

        verify(localRepository).getFavoritedTVShows();
        assertNotNull(result.data);
        assertEquals(tvShowResultsItems.size(), result.data.size());
    }
}