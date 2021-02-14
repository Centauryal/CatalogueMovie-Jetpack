package com.centaury.cataloguemovie.favorite.di.component

import com.centaury.cataloguemovie.di.MovieScope
import com.centaury.cataloguemovie.di.component.AppComponent
import com.centaury.cataloguemovie.favorite.di.module.FavoriteMovieModule
import com.centaury.cataloguemovie.favorite.fragment.FavoriteMovieFragment
import com.centaury.cataloguemovie.favorite.viewmodel.FavoriteViewModelModule
import dagger.Component

/**
 * @Author Centaury (alfa.arnialfa@gmail.com)
 * Created by Centaury on 7/27/2020.
 */
@MovieScope
@Component(
    dependencies = [AppComponent::class],
    modules = [FavoriteMovieModule::class, FavoriteViewModelModule::class]
)
interface FavoriteMovieComponent {
    fun inject(favoriteMovie: FavoriteMovieFragment)
}