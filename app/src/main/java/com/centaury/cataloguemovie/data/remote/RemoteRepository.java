package com.centaury.cataloguemovie.data.remote;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.centaury.cataloguemovie.BuildConfig;
import com.centaury.cataloguemovie.data.remote.detail.movie.DetailMovieResponse;
import com.centaury.cataloguemovie.data.remote.detail.tvshow.DetailTVShowResponse;
import com.centaury.cataloguemovie.data.remote.genre.GenreResponse;
import com.centaury.cataloguemovie.data.remote.genre.GenresItem;
import com.centaury.cataloguemovie.data.remote.movie.MovieResponse;
import com.centaury.cataloguemovie.data.remote.movie.MovieResultsItem;
import com.centaury.cataloguemovie.data.remote.tvshow.TVShowResponse;
import com.centaury.cataloguemovie.data.remote.tvshow.TVShowResultsItem;
import com.centaury.cataloguemovie.utils.EspressoIdlingResource;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.List;

/**
 * Created by Centaury on 10/24/2019.
 */
public class RemoteRepository {

    private static RemoteRepository INSTANCE;
    private String ApiKey = BuildConfig.API_KEY;

    public RemoteRepository(Application application) {
    }

    public static RemoteRepository getInstance(Application application) {
        if (INSTANCE == null) {
            INSTANCE = new RemoteRepository(application);
        }

        return INSTANCE;
    }

    public LiveData<ApiResponse<List<MovieResultsItem>>> getMovies(String language) {
        EspressoIdlingResource.increment();

        MutableLiveData<ApiResponse<List<MovieResultsItem>>> resultMovie = new MutableLiveData<>();

        AndroidNetworking.get(ApiEndPoint.ENDPOINT_DISCOVER_MOVIE)
                .addQueryParameter("api_key", ApiKey)
                .addQueryParameter("language", language)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        MovieResponse movieResponse = new Gson().fromJson(response + "", MovieResponse.class);
                        resultMovie.setValue(ApiResponse.success(movieResponse.getResults()));
                        if (!EspressoIdlingResource.getEspressoIdlingResource().isIdleNow()) {
                            EspressoIdlingResource.decrement();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        resultMovie.setValue(ApiResponse.error("onErrorMovie: " + anError.getErrorBody()));
                        if (!EspressoIdlingResource.getEspressoIdlingResource().isIdleNow()) {
                            EspressoIdlingResource.decrement();
                        }
                    }
                });

        return resultMovie;
    }

    public LiveData<ApiResponse<DetailMovieResponse>> getMovieDetail(String movieId, String language) {
        EspressoIdlingResource.increment();

        MutableLiveData<ApiResponse<DetailMovieResponse>> resultDetail = new MutableLiveData<>();

        AndroidNetworking.get(ApiEndPoint.ENDPOINT_DETAIL_MOVIE)
                .addPathParameter("movie_id", movieId)
                .addQueryParameter("api_key", ApiKey)
                .addQueryParameter("language", language)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        DetailMovieResponse resultsItem = new Gson().fromJson(response + "", DetailMovieResponse.class);
                        resultDetail.setValue(ApiResponse.success(resultsItem));
                        if (!EspressoIdlingResource.getEspressoIdlingResource().isIdleNow()) {
                            EspressoIdlingResource.decrement();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        resultDetail.setValue(ApiResponse.error("onErrorMovieDetail: " + anError.getErrorBody()));
                        if (!EspressoIdlingResource.getEspressoIdlingResource().isIdleNow()) {
                            EspressoIdlingResource.decrement();
                        }
                    }
                });

        return resultDetail;
    }

    public LiveData<ApiResponse<List<GenresItem>>> getGenreMovie(String language) {
        EspressoIdlingResource.increment();

        MutableLiveData<ApiResponse<List<GenresItem>>> resultGenre = new MutableLiveData<>();

        AndroidNetworking.get(ApiEndPoint.ENDPOINT_GENRE_MOVIE)
                .addQueryParameter("api_key", ApiKey)
                .addQueryParameter("language", language)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        GenreResponse genreResponse = new Gson().fromJson(response + "", GenreResponse.class);
                        resultGenre.setValue(ApiResponse.success(genreResponse.getGenres()));
                        if (!EspressoIdlingResource.getEspressoIdlingResource().isIdleNow()) {
                            EspressoIdlingResource.decrement();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        resultGenre.setValue(ApiResponse.error("onErrorGenreMovie: " + anError.getErrorBody()));
                        if (!EspressoIdlingResource.getEspressoIdlingResource().isIdleNow()) {
                            EspressoIdlingResource.decrement();
                        }
                    }
                });

        return resultGenre;
    }

    public LiveData<ApiResponse<List<TVShowResultsItem>>> getTVShows(String language) {
        EspressoIdlingResource.increment();

        MutableLiveData<ApiResponse<List<TVShowResultsItem>>> resultTVShow = new MutableLiveData<>();

        AndroidNetworking.get(ApiEndPoint.ENDPOINT_DISCOVER_TVSHOW)
                .addQueryParameter("api_key", ApiKey)
                .addQueryParameter("language", language)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        TVShowResponse tvShowResponse = new Gson().fromJson(response + "", TVShowResponse.class);
                        resultTVShow.setValue(ApiResponse.success(tvShowResponse.getResults()));
                        if (!EspressoIdlingResource.getEspressoIdlingResource().isIdleNow()) {
                            EspressoIdlingResource.decrement();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        resultTVShow.setValue(ApiResponse.error("onErrorTVShow: " + anError.getErrorBody()));
                        if (!EspressoIdlingResource.getEspressoIdlingResource().isIdleNow()) {
                            EspressoIdlingResource.decrement();
                        }
                    }
                });

        return resultTVShow;
    }

    public LiveData<ApiResponse<DetailTVShowResponse>> getTVShowDetail(String tvshowId, String language) {
        EspressoIdlingResource.increment();

        MutableLiveData<ApiResponse<DetailTVShowResponse>> resultDetail = new MutableLiveData<>();

        AndroidNetworking.get(ApiEndPoint.ENDPOINT_DETAIL_TVSHOW)
                .addPathParameter("tv_id", tvshowId)
                .addQueryParameter("api_key", ApiKey)
                .addQueryParameter("language", language)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        DetailTVShowResponse resultsItem = new Gson().fromJson(response + "", DetailTVShowResponse.class);
                        resultDetail.setValue(ApiResponse.success(resultsItem));
                        if (!EspressoIdlingResource.getEspressoIdlingResource().isIdleNow()) {
                            EspressoIdlingResource.decrement();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        resultDetail.setValue(ApiResponse.error("onErrorTVShowDetail: " + anError.getErrorBody()));
                        if (!EspressoIdlingResource.getEspressoIdlingResource().isIdleNow()) {
                            EspressoIdlingResource.decrement();
                        }
                    }
                });

        return resultDetail;
    }

    public LiveData<ApiResponse<List<GenresItem>>> getGenreTVShow(String language) {
        EspressoIdlingResource.increment();

        MutableLiveData<ApiResponse<List<GenresItem>>> resultGenre = new MutableLiveData<>();

        AndroidNetworking.get(ApiEndPoint.ENDPOINT_GENRE_TVSHOW)
                .addQueryParameter("api_key", ApiKey)
                .addQueryParameter("language", language)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        GenreResponse genreResponse = new Gson().fromJson(response + "", GenreResponse.class);
                        resultGenre.setValue(ApiResponse.success(genreResponse.getGenres()));
                        if (!EspressoIdlingResource.getEspressoIdlingResource().isIdleNow()) {
                            EspressoIdlingResource.decrement();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        resultGenre.setValue(ApiResponse.error("onErrorTVShowGenre: " + anError.getErrorBody()));
                        if (!EspressoIdlingResource.getEspressoIdlingResource().isIdleNow()) {
                            EspressoIdlingResource.decrement();
                        }
                    }
                });

        return resultGenre;
    }

}
