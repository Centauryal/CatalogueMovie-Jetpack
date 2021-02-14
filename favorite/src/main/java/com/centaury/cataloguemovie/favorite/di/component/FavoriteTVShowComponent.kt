package com.centaury.cataloguemovie.favorite.di.component

import com.centaury.cataloguemovie.di.MovieScope
import com.centaury.cataloguemovie.di.component.AppComponent
import com.centaury.cataloguemovie.favorite.di.module.FavoriteTVShowModule
import com.centaury.cataloguemovie.favorite.fragment.FavoriteTVShowFragment
import com.centaury.cataloguemovie.favorite.viewmodel.FavoriteViewModelModule
import dagger.Component

/**
 * @Author Centaury (alfa.arnialfa@gmail.com)
 * Created by Centaury on 7/27/2020.
 */
@MovieScope
@Component(
    dependencies = [AppComponent::class],
    modules = [FavoriteTVShowModule::class, FavoriteViewModelModule::class]
)
interface FavoriteTVShowComponent {
    fun inject(favoriteTVShow: FavoriteTVShowFragment)
}