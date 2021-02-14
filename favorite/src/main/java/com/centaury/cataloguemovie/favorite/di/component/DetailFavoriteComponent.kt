package com.centaury.cataloguemovie.favorite.di.component

import com.centaury.cataloguemovie.di.MovieScope
import com.centaury.cataloguemovie.di.component.AppComponent
import com.centaury.cataloguemovie.favorite.detail.DetailFavoriteMovieActivity
import com.centaury.cataloguemovie.favorite.detail.DetailFavoriteViewModelModule
import com.centaury.cataloguemovie.favorite.di.module.DetailFavoriteModule
import dagger.Component

/**
 * @Author Centaury (alfa.arnialfa@gmail.com)
 * Created by Centaury on 7/27/2020.
 */
@MovieScope
@Component(
    dependencies = [AppComponent::class],
    modules = [DetailFavoriteModule::class, DetailFavoriteViewModelModule::class]
)
interface DetailFavoriteComponent {
    fun inject(detailFavorite: DetailFavoriteMovieActivity)
}