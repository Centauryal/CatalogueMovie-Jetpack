package com.centaury.cataloguemovie.data;

import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.centaury.cataloguemovie.data.local.LocalRepository;
import com.centaury.cataloguemovie.data.local.entity.GenreMovieEntity;
import com.centaury.cataloguemovie.data.local.entity.GenreTVShowEntity;
import com.centaury.cataloguemovie.data.local.entity.MovieEntity;
import com.centaury.cataloguemovie.data.local.entity.TVShowEntity;
import com.centaury.cataloguemovie.data.remote.ApiResponse;
import com.centaury.cataloguemovie.data.remote.RemoteRepository;
import com.centaury.cataloguemovie.data.remote.detail.movie.DetailMovieResponse;
import com.centaury.cataloguemovie.data.remote.detail.tvshow.DetailTVShowResponse;
import com.centaury.cataloguemovie.data.remote.genre.GenresItem;
import com.centaury.cataloguemovie.data.remote.movie.MovieResultsItem;
import com.centaury.cataloguemovie.data.remote.tvshow.TVShowResultsItem;
import com.centaury.cataloguemovie.utils.AppExecutors;
import com.centaury.cataloguemovie.vo.Resource;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Centaury on 10/25/2019.
 */
public class CatalogueRepository implements CatalogueDataSource {

    private volatile static CatalogueRepository INSTANCE = null;

    private final LocalRepository localRepository;
    private final RemoteRepository remoteRepository;
    private final AppExecutors appExecutors;

    public CatalogueRepository(@NonNull LocalRepository localRepository, @NonNull RemoteRepository remoteRepository, AppExecutors appExecutors) {
        this.localRepository = localRepository;
        this.remoteRepository = remoteRepository;
        this.appExecutors = appExecutors;
    }

    public static CatalogueRepository getInstance(LocalRepository localRepository, RemoteRepository remoteData, AppExecutors appExecutors) {
        if (INSTANCE == null) {
            synchronized (RemoteRepository.class) {
                if (INSTANCE == null)
                    INSTANCE = new CatalogueRepository(localRepository, remoteData, appExecutors);
            }
        }

        return INSTANCE;
    }

    @Override
    public LiveData<Resource<List<MovieEntity>>> getMovies(String language) {
        return new NetworkBoundResource<List<MovieEntity>, List<MovieResultsItem>>(appExecutors) {

            @Override
            protected LiveData<List<MovieEntity>> loadFromDB() {
                return localRepository.getAllMovies();
            }

            @Override
            protected Boolean shouldFetch(List<MovieEntity> data) {
                return (data == null) || (data.size() == 0);
            }

            @Override
            protected LiveData<ApiResponse<List<MovieResultsItem>>> createCall() {
                return remoteRepository.getMovies(language);
            }

            @Override
            protected void saveCallResult(List<MovieResultsItem> data) {
                List<MovieEntity> movieEntities = new ArrayList<>();

                for (MovieResultsItem resultsItems : data) {
                    movieEntities.add(new MovieEntity(resultsItems.getId(),
                            resultsItems.getTitle(),
                            resultsItems.getOriginalTitle(),
                            resultsItems.getPosterPath(),
                            resultsItems.getBackdropPath(),
                            TextUtils.join(", ", resultsItems.getGenreIds()),
                            resultsItems.getReleaseDate(),
                            resultsItems.getOverview(),
                            resultsItems.getVoteAverage(),
                            null));
                }

                localRepository.insertMovie(movieEntities);
            }
        }.asLiveData();
    }

    @Override
    public LiveData<Resource<List<MovieEntity>>> getFavoritedMovies() {
        return new NetworkBoundResource<List<MovieEntity>, List<MovieResultsItem>>(appExecutors) {

            @Override
            protected LiveData<List<MovieEntity>> loadFromDB() {
                return localRepository.getFavoritedMovies();
            }

            @Override
            protected Boolean shouldFetch(List<MovieEntity> data) {
                return false;
            }

            @Override
            protected LiveData<ApiResponse<List<MovieResultsItem>>> createCall() {
                return null;
            }

            @Override
            protected void saveCallResult(List<MovieResultsItem> data) {

            }
        }.asLiveData();
    }

    @Override
    public LiveData<Resource<MovieEntity>> getDetailMovie(String movieId, String language) {
        return new NetworkBoundResource<MovieEntity, DetailMovieResponse>(appExecutors) {

            @Override
            protected LiveData<MovieEntity> loadFromDB() {
                return localRepository.getMovieById(Integer.parseInt(movieId));
            }

            @Override
            protected Boolean shouldFetch(MovieEntity data) {
                return (data == null);
            }

            @Override
            protected LiveData<ApiResponse<DetailMovieResponse>> createCall() {
                return remoteRepository.getMovieDetail(movieId, language);
            }

            @Override
            protected void saveCallResult(DetailMovieResponse data) {

            }
        }.asLiveData();
    }

    @Override
    public LiveData<Resource<List<GenreMovieEntity>>> getGenreMovie(String language) {
        return new NetworkBoundResource<List<GenreMovieEntity>, List<GenresItem>>(appExecutors) {

            @Override
            protected LiveData<List<GenreMovieEntity>> loadFromDB() {
                return localRepository.getAllGenresMovie();
            }

            @Override
            protected Boolean shouldFetch(List<GenreMovieEntity> data) {
                return (data == null) || (data.size() == 0);
            }

            @Override
            protected LiveData<ApiResponse<List<GenresItem>>> createCall() {
                return remoteRepository.getGenreMovie(language);
            }

            @Override
            protected void saveCallResult(List<GenresItem> data) {
                List<GenreMovieEntity> genreMovieEntities = new ArrayList<>();

                for (GenresItem genresItem : data) {
                    genreMovieEntities.add(new GenreMovieEntity(genresItem.getId(),
                            genresItem.getName()));
                }

                localRepository.insertGenresMovie(genreMovieEntities);
            }
        }.asLiveData();
    }

    @Override
    public LiveData<Resource<List<TVShowEntity>>> getTVShows(String language) {
        return new NetworkBoundResource<List<TVShowEntity>, List<TVShowResultsItem>>(appExecutors) {

            @Override
            protected LiveData<List<TVShowEntity>> loadFromDB() {
                return localRepository.getAllTVShows();
            }

            @Override
            protected Boolean shouldFetch(List<TVShowEntity> data) {
                return (data == null) || (data.size() == 0);
            }

            @Override
            protected LiveData<ApiResponse<List<TVShowResultsItem>>> createCall() {
                return remoteRepository.getTVShows(language);
            }

            @Override
            protected void saveCallResult(List<TVShowResultsItem> data) {
                List<TVShowEntity> tvShowEntities = new ArrayList<>();

                for (TVShowResultsItem resultsItems : data) {
                    tvShowEntities.add(new TVShowEntity(resultsItems.getId(),
                            resultsItems.getName(),
                            resultsItems.getOriginalName(),
                            resultsItems.getPosterPath(),
                            resultsItems.getBackdropPath(),
                            TextUtils.join(", ", resultsItems.getGenreIds()),
                            resultsItems.getFirstAirDate(),
                            resultsItems.getOverview(),
                            resultsItems.getVoteAverage(),
                            null));
                }

                localRepository.insertTVShow(tvShowEntities);
            }
        }.asLiveData();
    }

    @Override
    public LiveData<Resource<List<TVShowEntity>>> getFavoritedTVShows() {
        return new NetworkBoundResource<List<TVShowEntity>, List<TVShowResultsItem>>(appExecutors) {

            @Override
            protected LiveData<List<TVShowEntity>> loadFromDB() {
                return localRepository.getFavoritedTVShows();
            }

            @Override
            protected Boolean shouldFetch(List<TVShowEntity> data) {
                return false;
            }

            @Override
            protected LiveData<ApiResponse<List<TVShowResultsItem>>> createCall() {
                return null;
            }

            @Override
            protected void saveCallResult(List<TVShowResultsItem> data) {

            }
        }.asLiveData();
    }

    @Override
    public LiveData<Resource<TVShowEntity>> getDetailTVShow(String tvshowId, String language) {
        return new NetworkBoundResource<TVShowEntity, DetailTVShowResponse>(appExecutors) {

            @Override
            protected LiveData<TVShowEntity> loadFromDB() {
                return localRepository.getTVShowById(Integer.parseInt(tvshowId));
            }

            @Override
            protected Boolean shouldFetch(TVShowEntity data) {
                return (data == null);
            }

            @Override
            protected LiveData<ApiResponse<DetailTVShowResponse>> createCall() {
                return remoteRepository.getTVShowDetail(tvshowId, language);
            }

            @Override
            protected void saveCallResult(DetailTVShowResponse data) {

            }
        }.asLiveData();
    }

    @Override
    public LiveData<Resource<List<GenreTVShowEntity>>> getGenreTVShow(String language) {
        return new NetworkBoundResource<List<GenreTVShowEntity>, List<GenresItem>>(appExecutors) {

            @Override
            protected LiveData<List<GenreTVShowEntity>> loadFromDB() {
                return localRepository.getAllGenresTVShow();
            }

            @Override
            protected Boolean shouldFetch(List<GenreTVShowEntity> data) {
                return (data == null) || (data.size() == 0);
            }

            @Override
            protected LiveData<ApiResponse<List<GenresItem>>> createCall() {
                return remoteRepository.getGenreTVShow(language);
            }

            @Override
            protected void saveCallResult(List<GenresItem> data) {
                List<GenreTVShowEntity> genreTVShowEntities = new ArrayList<>();

                for (GenresItem genresItem : data) {
                    genreTVShowEntities.add(new GenreTVShowEntity(genresItem.getId(),
                            genresItem.getName()));
                }

                localRepository.insertGenresTVShow(genreTVShowEntities);
            }
        }.asLiveData();
    }

    @Override
    public void setFavoriteMovie(MovieEntity movieEntity, boolean state) {
        Runnable runnable = () -> localRepository.updateFavoritedMovie(movieEntity, state);

        appExecutors.diskIO().execute(runnable);
    }

    @Override
    public void setFavoriteTVShow(TVShowEntity tvShowEntity, boolean state) {
        Runnable runnable = () -> localRepository.updateFavoritedTVShow(tvShowEntity, state);

        appExecutors.diskIO().execute(runnable);
    }
}
