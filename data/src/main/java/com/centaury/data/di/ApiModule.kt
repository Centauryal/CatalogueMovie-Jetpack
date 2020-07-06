package com.centaury.data.di

import com.centaury.data.movies.repository.source.network.MoviesApi
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
}