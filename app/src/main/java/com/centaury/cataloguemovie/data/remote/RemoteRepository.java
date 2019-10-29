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
import com.centaury.cataloguemovie.data.remote.movie.MovieResponse;
import com.centaury.cataloguemovie.data.remote.movie.MovieResultsItem;
import com.centaury.cataloguemovie.data.remote.tvshow.TVShowResponse;
import com.centaury.cataloguemovie.data.remote.tvshow.TVShowResultsItem;
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

    public void getMovies(String language, LoadMoviesCallback callback) {
        AndroidNetworking.get(ApiEndPoint.ENDPOINT_DISCOVER_MOVIE)
                .addQueryParameter("api_key", ApiKey)
                .addQueryParameter("language", language)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        MovieResponse movieResponse = new Gson().fromJson(response + "", MovieResponse.class);
                        callback.onResponse(movieResponse.getResults());
                    }

                    @Override
                    public void onError(ANError anError) {
                        callback.onErrorResponse("onError: " + anError.getErrorBody());
                    }
                });
    }

    public void getMovieDetail(String movieId, String language, GetMovieDetailCallback callback) {
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
                    }

                    @Override
                    public void onError(ANError anError) {
                        callback.onErrorResponse("onError: " + anError.getErrorBody());
                    }
                });
    }

    public void getGenreMovie(String language, GetGenreMovieCallback callback) {
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
                    }

                    @Override
                    public void onError(ANError anError) {
                        callback.onErrorResponse("onError: " + anError.getErrorBody());
                    }
                });
    }

    public void getTVShows(String language, LoadTVShowsCallback callback) {
        AndroidNetworking.get(ApiEndPoint.ENDPOINT_DISCOVER_TVSHOW)
                .addQueryParameter("api_key", ApiKey)
                .addQueryParameter("language", language)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        TVShowResponse tvShowResponse = new Gson().fromJson(response + "", TVShowResponse.class);
                        callback.onResponse(tvShowResponse.getResults());
                    }

                    @Override
                    public void onError(ANError anError) {
                        callback.onErrorResponse("onError: " + anError.getErrorBody());
                    }
                });
    }

    public void getTVShowDetail(String tvshowId, String language, GetTVShowDetailCallback callback) {
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
                    }

                    @Override
                    public void onError(ANError anError) {
                        callback.onErrorResponse("onError: " + anError.getErrorBody());
                    }
                });
    }

    public void getGenreTVShow(String language, GetGenreTVShowCallback callback) {
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
                    }

                    @Override
                    public void onError(ANError anError) {
                        callback.onErrorResponse("onError: " + anError.getErrorBody());
                    }
                });
    }

    public interface LoadMoviesCallback {
        void onResponse(List<MovieResultsItem> movieResultsItemList);

        void onErrorResponse(String message);
    }

    public interface GetMovieDetailCallback {
        void onResponse(DetailMovieResponse detailMovieResponse);

        void onErrorResponse(String message);
    }

    public interface GetGenreMovieCallback {
        void onResponse(List<GenresItem> genresItemList);

        void onErrorResponse(String message);
    }

    public interface LoadTVShowsCallback {
        void onResponse(List<TVShowResultsItem> tvShowResultsItemList);

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
