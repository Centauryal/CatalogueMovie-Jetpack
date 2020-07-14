package com.centaury.cataloguemovie.di.module

import com.centaury.cataloguemovie.ui.movie.MovieContract
import com.centaury.cataloguemovie.ui.movie.MovieViewModel
import dagger.Module
import dagger.Provides

/**
 * @Author Centaury (alfa.arnialfa@gmail.com)
 * Created by Centaury on 7/6/2020.
 */
@Module
class DiscoverMovieModule {

    @Provides
    fun provideMovieViewModel(movieViewModel: MovieViewModel): MovieContract = movieViewModel
}