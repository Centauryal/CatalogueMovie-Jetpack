package com.centaury.cataloguemovie.di.module

import android.app.Application
import android.content.Context
import com.centaury.data.detail.repository.DetailEntityRepository
import com.centaury.data.genre.repository.GenreEntityRepository
import com.centaury.data.movies.repository.MoviesEntityRepository
import com.centaury.data.tvshow.repository.TVShowsEntityRepository
import com.centaury.domain.detail.DetailRepository
import com.centaury.domain.genre.GenreRepository
import com.centaury.domain.movies.MoviesRepository
import com.centaury.domain.tvshow.TVShowsRepository
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

    @Provides
    @Singleton
    fun provideTVShowsRepository(tvShowEntityRepository: TVShowsEntityRepository): TVShowsRepository =
        tvShowEntityRepository

    @Provides
    @Singleton
    fun provideGenreRepository(genreEntityRepository: GenreEntityRepository): GenreRepository =
        genreEntityRepository

    @Provides
    @Singleton
    fun provideDetailRepository(detailEntityRepository: DetailEntityRepository): DetailRepository =
        detailEntityRepository
}