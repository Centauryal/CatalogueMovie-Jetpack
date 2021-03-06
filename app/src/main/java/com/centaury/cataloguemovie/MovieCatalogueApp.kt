package com.centaury.cataloguemovie

import android.app.Application
import com.centaury.cataloguemovie.di.component.AppComponent
import com.centaury.cataloguemovie.di.component.DaggerAppComponent
import com.centaury.cataloguemovie.di.module.ApplicationModule
import com.centaury.data.di.ApiModule
import com.centaury.data.di.DatabaseModule
import com.centaury.data.di.NetworkModule
import timber.log.Timber

/**
 * Created by Centaury on 10/24/2019.
 */
class MovieCatalogueApp : Application() {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder()
            .applicationModule(ApplicationModule(this))
            .apiModule(ApiModule())
            .networkModule(NetworkModule())
            .databaseModule(DatabaseModule())
            .build()
    }

    override fun onCreate() {
        super.onCreate()

        appComponent.inject(this)

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}