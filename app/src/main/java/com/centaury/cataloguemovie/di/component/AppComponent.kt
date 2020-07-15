package com.centaury.cataloguemovie.di.component

import android.app.Application
import android.content.Context
import com.centaury.cataloguemovie.MovieCatalogueApp
import com.centaury.cataloguemovie.di.module.ApplicationModule
import com.centaury.data.di.ApiModule
import com.centaury.data.di.NetworkModule
import com.centaury.domain.genre.GenreRepository
import com.centaury.domain.movies.MoviesRepository
import com.centaury.domain.tvshow.TVShowsRepository
import dagger.Component
import javax.inject.Singleton

/**
 * Created by Centaury on 1/17/2020.
 */
@Singleton
@Component(
    modules = [
        ApplicationModule::class,
        NetworkModule::class,
        ApiModule::class
    ]
)
interface AppComponent {
    fun inject(movieCatalogueApp: MovieCatalogueApp)

    fun context(): Context

    fun application(): Application

    fun moviesRepository(): MoviesRepository

    fun tvShowRepository(): TVShowsRepository

    fun genreRepository(): GenreRepository
}