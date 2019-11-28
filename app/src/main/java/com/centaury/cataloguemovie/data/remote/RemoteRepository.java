package com.centaury.cataloguemovie.data.remote;

import android.app.Application;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.centaury.cataloguemovie.BuildConfig;
import com.centaury.cataloguemovie.data.remote.detail.movie.DetailMovieResponse;
import com.centaury.cataloguemovie.data.remote.detail.tvshow.DetailTVShowResponse;
import com.centaury.cataloguemovie.data.remote.genre.GenreResponse;
import com.centaury.cataloguemovie.data.remote.genre.GenresItem;
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

    public void getMovieDetail(String movieId, String language, GetMovieDetailCallback callback) {
        EspressoIdlingResource.increment();

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
                        callback.onResponse(resultsItem);
                        if (!EspressoIdlingResource.getEspressoIdlingResource().isIdleNow()) {
                            EspressoIdlingResource.decrement();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        callback.onErrorResponse("onError: " + anError.getErrorBody());
                        if (!EspressoIdlingResource.getEspressoIdlingResource().isIdleNow()) {
                            EspressoIdlingResource.decrement();
                        }
                    }
                });
    }

    public void getGenreMovie(String language, GetGenreMovieCallback callback) {
        EspressoIdlingResource.increment();

        AndroidNetworking.get(ApiEndPoint.ENDPOINT_GENRE_MOVIE)
                .addQueryParameter("api_key", ApiKey)
                .addQueryParameter("language", language)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        GenreResponse genreResponse = new Gson().fromJson(response + "", GenreResponse.class);
                        callback.onResponse(genreResponse.getGenres());
                        if (!EspressoIdlingResource.getEspressoIdlingResource().isIdleNow()) {
                            EspressoIdlingResource.decrement();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        callback.onErrorResponse("onError: " + anError.getErrorBody());
                        if (!EspressoIdlingResource.getEspressoIdlingResource().isIdleNow()) {
                            EspressoIdlingResource.decrement();
                        }
                    }
                });
    }

    public void getTVShowDetail(String tvshowId, String language, GetTVShowDetailCallback callback) {
        EspressoIdlingResource.increment();

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
                        callback.onResponse(resultsItem);
                        if (!EspressoIdlingResource.getEspressoIdlingResource().isIdleNow()) {
                            EspressoIdlingResource.decrement();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        callback.onErrorResponse("onError: " + anError.getErrorBody());
                        if (!EspressoIdlingResource.getEspressoIdlingResource().isIdleNow()) {
                            EspressoIdlingResource.decrement();
                        }
                    }
                });
    }

    public void getGenreTVShow(String language, GetGenreTVShowCallback callback) {
        EspressoIdlingResource.increment();

        AndroidNetworking.get(ApiEndPoint.ENDPOINT_GENRE_TVSHOW)
                .addQueryParameter("api_key", ApiKey)
                .addQueryParameter("language", language)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        GenreResponse genreResponse = new Gson().fromJson(response + "", GenreResponse.class);
                        callback.onResponse(genreResponse.getGenres());
                        if (!EspressoIdlingResource.getEspressoIdlingResource().isIdleNow()) {
                            EspressoIdlingResource.decrement();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        callback.onErrorResponse("onError: " + anError.getErrorBody());
                        if (!EspressoIdlingResource.getEspressoIdlingResource().isIdleNow()) {
                            EspressoIdlingResource.decrement();
                        }
                    }
                });
    }

    public interface GetMovieDetailCallback {
        void onResponse(DetailMovieResponse detailMovieResponse);

        void onErrorResponse(String message);
    }

    public interface GetGenreMovieCallback {
        void onResponse(List<GenresItem> genresItemList);

        void onErrorResponse(String message);
    }

    public interface GetTVShowDetailCallback {
        void onResponse(DetailTVShowResponse detailTVShowResponse);

        void onErrorResponse(String message);
    }

    public interface GetGenreTVShowCallback {
        void onResponse(List<GenresItem> genresItemList);

        void onErrorResponse(String message);
    }

}
