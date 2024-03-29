package com.centaury.data.di

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
}