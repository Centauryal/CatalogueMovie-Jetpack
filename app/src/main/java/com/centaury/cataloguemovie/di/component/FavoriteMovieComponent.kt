package com.centaury.cataloguemovie.di.component

import com.centaury.cataloguemovie.di.MovieScope
import com.centaury.cataloguemovie.di.module.FavoriteMovieModule
import com.centaury.cataloguemovie.ui.favorite.fragment.FavoriteMovieFragment
import com.centaury.cataloguemovie.ui.favorite.viewmodel.FavoriteMovieViewModelModule
import dagger.Component

/**
 * @Author Centaury (alfa.arnialfa@gmail.com)
 * Created by Centaury on 7/27/2020.
 */
@MovieScope
@Component(
    dependencies = [AppComponent::class],
    modules = [FavoriteMovieModule::class, FavoriteMovieViewModelModule::class]
)
interface FavoriteMovieComponent {
    fun inject(favoriteMovie: FavoriteMovieFragment)
}