package com.centaury.cataloguemovie.di.component

import com.centaury.cataloguemovie.di.MovieScope
import com.centaury.cataloguemovie.di.module.DetailFavoriteModule
import com.centaury.cataloguemovie.ui.detail.DetailFavoriteMovieActivity
import com.centaury.cataloguemovie.ui.detail.DetailMovieViewModelModule
import dagger.Component

/**
 * @Author Centaury (alfa.arnialfa@gmail.com)
 * Created by Centaury on 7/27/2020.
 */
@MovieScope
@Component(
    dependencies = [AppComponent::class],
    modules = [DetailFavoriteModule::class, DetailMovieViewModelModule::class]
)
interface DetailFavoriteComponent {
    fun inject(detailFavorite: DetailFavoriteMovieActivity)
}