package com.centaury.data.di

import com.centaury.data.detail.repository.source.network.DetailApi
import com.centaury.data.genre.repository.source.network.GenreApi
import com.centaury.data.movies.repository.source.network.MoviesApi
import com.centaury.data.tvshow.repository.source.network.TVShowApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * @Author Centaury (alfa.arnialfa@gmail.com)
 * Created by Centaury on 7/3/2020.
 */
@Module
class ApiModule {

    @Provides
    @Singleton
    fun provideMovieApi(builder: Retrofit.Builder): MoviesApi =
        builder
            .build()
            .create(MoviesApi::class.java)

    @Provides
    @Singleton
    fun provideTVShowApi(builder: Retrofit.Builder): TVShowApi =
        builder
            .build()
            .create(TVShowApi::class.java)

    @Provides
    @Singleton
    fun provideGenreApi(builder: Retrofit.Builder): GenreApi =
        builder
            .build()
            .create(GenreApi::class.java)

    @Provides
    @Singleton
    fun provideDetailApi(builder: Retrofit.Builder): DetailApi =
        builder
            .build()
            .create(DetailApi::class.java)
}