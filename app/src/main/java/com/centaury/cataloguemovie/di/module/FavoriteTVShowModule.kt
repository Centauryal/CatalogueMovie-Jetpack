package com.centaury.cataloguemovie.di.module

import com.centaury.cataloguemovie.ui.favorite.viewmodel.FavoriteTVShowContract
import com.centaury.cataloguemovie.ui.favorite.viewmodel.FavoriteTVShowViewModel
import dagger.Module
import dagger.Provides

/**
 * @Author Centaury (alfa.arnialfa@gmail.com)
 * Created by Centaury on 7/27/2020.
 */
@Module
class FavoriteTVShowModule {

    @Provides
    fun provideFavoriteTVShowViewModel(favoriteTVShowViewModel: FavoriteTVShowViewModel): FavoriteTVShowContract =
        favoriteTVShowViewModel
}