package com.centaury.cataloguemovie;

import android.app.Activity;
import android.app.Application;

import com.androidnetworking.AndroidNetworking;
import com.centaury.cataloguemovie.di.AppInjector;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

/**
 * Created by Centaury on 10/24/2019.
 */
public class MovieCatalogueApp extends Application implements HasActivityInjector {

    @Inject
    DispatchingAndroidInjector<Activity> dispatchingAndroidInjector;

    @Override
    public void onCreate() {
        super.onCreate();

        AppInjector.init(this);

        AndroidNetworking.initialize(getApplicationContext());
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return dispatchingAndroidInjector;
    }
}
