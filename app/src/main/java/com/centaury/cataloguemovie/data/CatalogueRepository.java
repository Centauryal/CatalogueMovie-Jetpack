package com.centaury.cataloguemovie.data;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.centaury.cataloguemovie.data.remote.RemoteRepository;
import com.centaury.cataloguemovie.data.remote.detail.movie.DetailMovieResponse;
import com.centaury.cataloguemovie.data.remote.detail.tvshow.DetailTVShowResponse;
import com.centaury.cataloguemovie.data.remote.genre.GenresItem;
import com.centaury.cataloguemovie.data.remote.movie.MovieResultsItem;
import com.centaury.cataloguemovie.data.remote.tvshow.TVShowResultsItem;

import java.util.List;

/**
 * Created by Centaury on 10/25/2019.
 */
public class CatalogueRepository implements CatalogueDataSource {

    private volatile static CatalogueRepository INSTANCE = null;
    private final RemoteRepository remoteRepository;

    public CatalogueRepository(RemoteRepository remoteRepository) {
        this.remoteRepository = remoteRepository;
    }

    public static CatalogueRepository getInstance(RemoteRepository remoteData) {
        if (INSTANCE == null) {
            synchronized (RemoteRepository.class) {
                if (INSTANCE == null)
                    INSTANCE = new CatalogueRepository(remoteData);
            }
        }

        return INSTANCE;
    }

    @Override
    public LiveData<List<MovieResultsItem>> getMovies(String language) {
        MutableLiveData<List<MovieResultsItem>> movieResults = new MutableLiveData<>();

        remoteRepository.getMovies(language, new RemoteRepository.LoadMoviesCallback() {
            @Override
            public void onResponse(List<MovieResultsItem> movieResultsItemList) {
                movieResults.postValue(movieResultsItemList);
            }

            @Override
            public void onErrorResponse(String message) {
                Log.e("onErrorResponse: ", message);
            }
        });

        return movieResults;
    }

    @Override
    public LiveData<DetailMovieResponse> getDetailMovie(String movieId, String language) {
        MutableLiveData<DetailMovieResponse> movieResult = new MutableLiveData<>();

        remoteRepository.getMovieDetail(movieId, language, new RemoteRepository.GetMovieDetailCallback() {
            @Override
            public void onResponse(DetailMovieResponse detailMovieResponse) {
                movieResult.postValue(detailMovieResponse);
            }

            @Override
            public void onErrorResponse(String message) {
                Log.e("onErrorResponse: ", message);
            }
        });

        return movieResult;
    }

    @Override
    public LiveData<List<GenresItem>> getGenreMovie(String language) {
        MutableLiveData<List<GenresItem>> genreResults = new MutableLiveData<>();

        remoteRepository.getGenreMovie(language, new RemoteRepository.GetGenreMovieCallback() {
            @Override
            public void onResponse(List<GenresItem> genresItemList) {
                genreResults.postValue(genresItemList);
            }

            @Override
            public void onErrorResponse(String message) {
                Log.e("onErrorResponse: ", message);
            }
        });

        return genreResults;
    }

    @Override
    public LiveData<List<TVShowResultsItem>> getTVShows(String language) {
        MutableLiveData<List<TVShowResultsItem>> tvshowResults = new MutableLiveData<>();

        remoteRepository.getTVShows(language, new RemoteRepository.LoadTVShowsCallback() {
            @Override
            public void onResponse(List<TVShowResultsItem> tvShowResultsItemList) {
                tvshowResults.postValue(tvShowResultsItemList);
            }

            @Override
            public void onErrorResponse(String message) {
                Log.e("onErrorResponse: ", message);
            }
        });

        return tvshowResults;
    }

    @Override
    public LiveData<DetailTVShowResponse> getDetailTVShow(String tvshowId, String language) {
        MutableLiveData<DetailTVShowResponse> tvshowResult = new MutableLiveData<>();

        remoteRepository.getTVShowDetail(tvshowId, language, new RemoteRepository.GetTVShowDetailCallback() {
            @Override
            public void onResponse(DetailTVShowResponse detailTVShowResponse) {
                tvshowResult.postValue(detailTVShowResponse);
            }

            @Override
            public void onErrorResponse(String message) {
                Log.e("onErrorResponse: ", message);
            }
        });

        return tvshowResult;
    }

    @Override
    public LiveData<List<GenresItem>> getGenreTVShow(String language) {
        MutableLiveData<List<GenresItem>> genreResults = new MutableLiveData<>();

        remoteRepository.getGenreTVShow(language, new RemoteRepository.GetGenreTVShowCallback() {
            @Override
            public void onResponse(List<GenresItem> genresItemList) {
                genreResults.postValue(genresItemList);
            }

            @Override
            public void onErrorResponse(String message) {
                Log.e("onErrorResponse: ", message);
            }
        });

        return genreResults;
    }
}
