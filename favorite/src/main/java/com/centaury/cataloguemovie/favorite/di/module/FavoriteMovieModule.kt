package com.centaury.cataloguemovie.favorite.di.module

import com.centaury.cataloguemovie.favorite.viewmodel.FavoriteMovieContract
import com.centaury.cataloguemovie.favorite.viewmodel.FavoriteMovieViewModel
import dagger.Module
import dagger.Provides

/**
 * @Author Centaury (alfa.arnialfa@gmail.com)
 * Created by Centaury on 7/27/2020.
 */
@Module
class FavoriteMovieModule {

    @Provides
    fun provideFavoriteMovieViewModel(favoriteMovieViewModel: FavoriteMovieViewModel): FavoriteMovieContract =
        favoriteMovieViewModel
}