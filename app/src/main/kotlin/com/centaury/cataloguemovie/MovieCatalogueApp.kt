package com.centaury.cataloguemovie

import android.app.Application
import com.centaury.cataloguemovie.di.component.AppComponent
import dagger.hilt.EntryPoints
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

/**
 * Created by Centaury on 10/24/2019.
 */
@HiltAndroidApp
class MovieCatalogueApp : Application() {

    val appComponent: AppComponent by lazy {
        EntryPoints.get(this, AppComponent::class.java)
    }

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}