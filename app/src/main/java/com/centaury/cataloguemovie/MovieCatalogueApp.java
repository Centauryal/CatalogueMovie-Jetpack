package com.centaury.cataloguemovie;

import android.app.Application;

import com.androidnetworking.AndroidNetworking;

/**
 * Created by Centaury on 10/24/2019.
 */
public class MovieCatalogueApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        AndroidNetworking.initialize(getApplicationContext());
    }
}
