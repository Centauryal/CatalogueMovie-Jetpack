package com.centaury.cataloguemovie.favorite.di.module

import com.centaury.cataloguemovie.favorite.viewmodel.FavoriteTVShowContract
import com.centaury.cataloguemovie.favorite.viewmodel.FavoriteTVShowViewModel
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