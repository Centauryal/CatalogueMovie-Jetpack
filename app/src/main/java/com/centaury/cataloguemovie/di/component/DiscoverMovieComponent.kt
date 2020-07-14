package com.centaury.cataloguemovie.di.component

import com.centaury.cataloguemovie.di.MovieScope
import com.centaury.cataloguemovie.di.module.DiscoverMovieModule
import com.centaury.cataloguemovie.ui.movie.MovieFragment
import com.centaury.cataloguemovie.ui.movie.MovieViewModelModule
import dagger.Component

/**
 * @Author Centaury (alfa.arnialfa@gmail.com)
 * Created by Centaury on 7/6/2020.
 */
@MovieScope
@Component(
    dependencies = [AppComponent::class],
    modules = [DiscoverMovieModule::class, MovieViewModelModule::class]
)
interface DiscoverMovieComponent {
    fun inject(discoverMovie: MovieFragment)
}