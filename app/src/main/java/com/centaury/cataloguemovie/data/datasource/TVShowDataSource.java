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
import com.centaury.cataloguemovie.data.remote.tvshow.TVShowResponse;
import com.centaury.cataloguemovie.data.remote.tvshow.TVShowResultsItem;
import com.centaury.cataloguemovie.utils.EspressoIdlingResource;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.Locale;

/**
 * Created by Centaury on 11/28/2019.
 */
public class TVShowDataSource extends PageKeyedDataSource<Integer, TVShowResultsItem> {

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
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, TVShowResultsItem> callback) {
        loadingState.postValue(true);

        EspressoIdlingResource.increment();

        AndroidNetworking.get(ApiEndPoint.ENDPOINT_DISCOVER_TVSHOW)
                .addQueryParameter("api_key", apiKey)
                .addQueryParameter("language", language)
                .addQueryParameter("page", "1")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        TVShowResponse tvShowResponse = new Gson().fromJson(response + "", TVShowResponse.class);
                        callback.onResult(tvShowResponse.getResults(), null, tvShowResponse.getPage() + 1);
                        loadingState.postValue(false);
                        if (!EspressoIdlingResource.getEspressoIdlingResource().isIdleNow()) {
                            EspressoIdlingResource.decrement();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.e("onError: ", anError.getErrorBody());
                        loadingState.postValue(false);
                        if (!EspressoIdlingResource.getEspressoIdlingResource().isIdleNow()) {
                            EspressoIdlingResource.decrement();
                        }
                    }
                });
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, TVShowResultsItem> callback) {

    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, TVShowResultsItem> callback) {

        if (params.key == -1) return;

        loadMoreLoadingState.postValue(true);

        EspressoIdlingResource.increment();

        AndroidNetworking.get(ApiEndPoint.ENDPOINT_DISCOVER_TVSHOW)
                .addQueryParameter("api_key", apiKey)
                .addQueryParameter("language", language)
                .addQueryParameter("page", "1")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        TVShowResponse tvShowResponse = new Gson().fromJson(response + "", TVShowResponse.class);
                        callback.onResult(tvShowResponse.getResults(), tvShowResponse.getPage() + 1);
                        loadMoreLoadingState.postValue(false);
                        if (!EspressoIdlingResource.getEspressoIdlingResource().isIdleNow()) {
                            EspressoIdlingResource.decrement();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.e("onError: ", anError.getErrorBody());
                        loadMoreLoadingState.postValue(false);
                        if (!EspressoIdlingResource.getEspressoIdlingResource().isIdleNow()) {
                            EspressoIdlingResource.decrement();
                        }
                    }
                });
    }
}
