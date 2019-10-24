package com.centaury.cataloguemovie.data.remote;

import android.app.Application;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.centaury.cataloguemovie.BuildConfig;
import com.centaury.cataloguemovie.data.remote.genre.GenresItem;
import com.centaury.cataloguemovie.data.remote.movie.MovieResultsItem;
import com.centaury.cataloguemovie.data.remote.tvshow.TVShowResultsItem;

import org.json.JSONObject;

import java.util.List;

/**
 * Created by Centaury on 10/24/2019.
 */
public class RemoteRepository {

    private static RemoteRepository INSTANCE;
    private Application application;

    public RemoteRepository(Application application) {
        this.application = application;
    }

    public static RemoteRepository getInstance(Application application) {
        if (INSTANCE == null) {
            INSTANCE = new RemoteRepository(application);
        }

        return INSTANCE;
    }

    public void getMovies(String language, LoadMoviesCallback callback) {
        AndroidNetworking.get(ApiEndPoint.ENDPOINT_DISCOVER_MOVIE)
                .addQueryParameter("api_key", BuildConfig.API_KEY)
                .addQueryParameter("language", language)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {

                    }

                    @Override
                    public void onError(ANError anError) {

                    }
                });
    }

    public interface LoadMoviesCallback {
        void onResponse(List<MovieResultsItem> movieResultsItemList);

        void onErrorResponse();
    }

    public interface GetMovieDetailCallback {
        void onResponse(MovieResultsItem movieResultsItem);

        void onErrorResponse();
    }

    public interface GetGenreMovieCallback {
        void onResponse(List<GenresItem> genresItemList);

        void onErrorResponse();
    }

    public interface LoadTVShowsCallback {
        void onResponse(List<TVShowResultsItem> tvShowResultsItemList);

        void onErrorResponse();
    }

    public interface GetTVShowCallback {
        void onResponse(TVShowResultsItem tvShowResultsItem);

        void onErrorResponse();
    }

    public interface GetGenreTVShowCallback {
        void onResponse(List<GenresItem> genresItemList);

        void onErrorResponse();
    }

}
