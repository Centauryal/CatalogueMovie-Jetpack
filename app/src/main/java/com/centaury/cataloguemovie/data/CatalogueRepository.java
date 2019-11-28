package com.centaury.cataloguemovie.data;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.centaury.cataloguemovie.data.local.LocalRepository;
import com.centaury.cataloguemovie.data.local.entity.MovieEntity;
import com.centaury.cataloguemovie.data.local.entity.TVShowEntity;
import com.centaury.cataloguemovie.data.remote.RemoteRepository;
import com.centaury.cataloguemovie.data.remote.detail.movie.DetailMovieResponse;
import com.centaury.cataloguemovie.data.remote.detail.tvshow.DetailTVShowResponse;
import com.centaury.cataloguemovie.data.remote.genre.GenresItem;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by Centaury on 10/25/2019.
 */
public class CatalogueRepository implements CatalogueDataSource {

    private volatile static CatalogueRepository INSTANCE = null;

    private final LocalRepository localRepository;
    private final RemoteRepository remoteRepository;

    public CatalogueRepository(@NonNull LocalRepository localRepository, @NonNull RemoteRepository remoteRepository) {
        this.localRepository = localRepository;
        this.remoteRepository = remoteRepository;
    }

    public static CatalogueRepository getInstance(LocalRepository localRepository, RemoteRepository remoteData) {
        if (INSTANCE == null) {
            synchronized (RemoteRepository.class) {
                if (INSTANCE == null)
                    INSTANCE = new CatalogueRepository(localRepository, remoteData);
            }
        }

        return INSTANCE;
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

    @Override
    public LiveData<PagedList<MovieEntity>> getFavoriteMovies() {
        return new LivePagedListBuilder<>(localRepository.getAllFavMovies(), 20).build();
    }

    @Override
    public MovieEntity getDetailFavMovie(int movieId) throws ExecutionException, InterruptedException {
        return localRepository.getFavMovieById(movieId);
    }

    @Override
    public void insertFavMovie(MovieEntity movieEntity) {
        localRepository.insertFavMovie(movieEntity);
    }

    @Override
    public void deleteFavMovie(MovieEntity movieEntity) {
        localRepository.deleteFavMovie(movieEntity);
    }

    @Override
    public LiveData<PagedList<TVShowEntity>> getFavoriteTVShows() {
        return new LivePagedListBuilder<>(localRepository.getAllFavTVShows(), 20).build();
    }

    @Override
    public TVShowEntity getDetailFavTVShow(int tvshowId) throws ExecutionException, InterruptedException {
        return localRepository.getFavTVShowById(tvshowId);
    }

    @Override
    public void insertFavTVShow(TVShowEntity tvShowEntity) {
        localRepository.insertFavTVShow(tvShowEntity);
    }

    @Override
    public void deleteFavTVShow(TVShowEntity tvShowEntity) {
        localRepository.deleteFavTVShow(tvShowEntity);
    }
}
