package com.centaury.cataloguemovie.di.module

import android.app.Application
import android.content.Context
import com.centaury.data.movies.repository.MoviesEntityRepository
import com.centaury.domain.movies.MoviesRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Centaury on 1/17/2020.
 */
@Module
class ApplicationModule(private val application: Application) {

    @Provides
    @Singleton
    fun provideApplicationContext(): Context = application

    @Provides
    @Singleton
    fun provideApplication() = application

    @Provides
    @Singleton
    fun provideMoviesRepository(moviesEntityRepository: MoviesEntityRepository): MoviesRepository =
        moviesEntityRepository
}