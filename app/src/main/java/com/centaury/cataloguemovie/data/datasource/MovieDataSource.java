package com.centaury.cataloguemovie.data.datasource;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.PageKeyedDataSource;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.centaury.cataloguemovie.BuildConfig;
import com.centaury.cataloguemovie.data.remote.ApiEndPoint;
import com.centaury.cataloguemovie.data.remote.movie.MovieResponse;
import com.centaury.cataloguemovie.data.remote.movie.MovieResultsItem;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.Locale;

/**
 * Created by Centaury on 11/27/2019.
 */
public class MovieDataSource extends PageKeyedDataSource<Integer, MovieResultsItem> {

    private MutableLiveData loadingState = new MutableLiveData();
    private MutableLiveData loadMoreLoadingState = new MutableLiveData();

    private String apiKey = BuildConfig.API_KEY;
    private String language = Locale.getDefault().toLanguageTag();

    public MutableLiveData getLoadingState() {
        return loadingState;
    }

    public MutableLiveData getLoadMoreLoadingState() {
        return loadMoreLoadingState;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, MovieResultsItem> callback) {

        loadingState.postValue(true);

        AndroidNetworking.get(ApiEndPoint.ENDPOINT_DISCOVER_MOVIE)
                .addQueryParameter("api_key", apiKey)
                .addQueryParameter("language", language)
                .addQueryParameter("page", "1")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        MovieResponse movieResponse = new Gson().fromJson(response + "", MovieResponse.class);
                        callback.onResult(movieResponse.getResults(), null, movieResponse.getPage() + 1);
                        loadingState.postValue(false);
                    }

                    @Override
                    public void onError(ANError anError) {
                        loadingState.postValue(false);
                    }
                });
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, MovieResultsItem> callback) {

    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, MovieResultsItem> callback) {

        Log.i("PagedList", "Loading Rang " + params.key + " Count " + params.requestedLoadSize);

        if (params.key == -1) return;

        loadMoreLoadingState.postValue(true);

        AndroidNetworking.get(ApiEndPoint.ENDPOINT_DISCOVER_MOVIE)
                .addQueryParameter("api_key", apiKey)
                .addQueryParameter("language", language)
                .addQueryParameter("page", String.valueOf(params.key))
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        MovieResponse movieResponse = new Gson().fromJson(response + "", MovieResponse.class);
                        callback.onResult(movieResponse.getResults(), movieResponse.getPage() + 1);
                        loadMoreLoadingState.postValue(false);
                    }

                    @Override
                    public void onError(ANError anError) {
                        loadMoreLoadingState.postValue(false);
                    }
                });

    }
}
