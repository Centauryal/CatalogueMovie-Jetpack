package com.centaury.cataloguemovie.di.component

import com.centaury.cataloguemovie.di.MovieScope
import com.centaury.cataloguemovie.di.module.FavoriteTVShowModule
import com.centaury.cataloguemovie.ui.favorite.fragment.FavoriteTVShowFragment
import com.centaury.cataloguemovie.ui.favorite.viewmodel.FavoriteTVShowViewModelModule
import dagger.Component

/**
 * @Author Centaury (alfa.arnialfa@gmail.com)
 * Created by Centaury on 7/27/2020.
 */
@MovieScope
@Component(
    dependencies = [AppComponent::class],
    modules = [FavoriteTVShowModule::class, FavoriteTVShowViewModelModule::class]
)
interface FavoriteTVShowComponent {
    fun inject(favoriteTVShow: FavoriteTVShowFragment)
}