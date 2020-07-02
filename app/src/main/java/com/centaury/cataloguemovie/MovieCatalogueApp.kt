package com.centaury.cataloguemovie

import android.app.Activity
import android.app.Application
import com.androidnetworking.AndroidNetworking
import com.centaury.cataloguemovie.di.AppInjector
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

/**
 * Created by Centaury on 10/24/2019.
 */
class MovieCatalogueApp : Application(), HasActivityInjector {
    @JvmField
    @Inject
    var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>? = null
    override fun onCreate() {
        super.onCreate()
        AppInjector.init(this)
        AndroidNetworking.initialize(applicationContext)
    }

    fun activityInjector(): AndroidInjector<Activity>? {
        return dispatchingAndroidInjector
    }
}