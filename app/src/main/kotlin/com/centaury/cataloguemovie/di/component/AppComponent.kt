package com.centaury.cataloguemovie.di.component

import android.app.Application
import android.content.Context
import com.centaury.domain.movies.MoviesRepository
import com.centaury.domain.tvshow.TVShowsRepository
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Created by Centaury on 1/17/2020.
 */
@EntryPoint
@InstallIn(SingletonComponent::class)
interface AppComponent {
    fun context(): Context

    fun application(): Application

    fun moviesRepository(): MoviesRepository

    fun tvShowRepository(): TVShowsRepository
}