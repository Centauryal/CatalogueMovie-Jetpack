package com.centaury.cataloguemovie.favorite.di.module

import com.centaury.cataloguemovie.favorite.detail.DetailFavoriteContract
import com.centaury.cataloguemovie.favorite.detail.DetailFavoriteMovieViewModel
import dagger.Module
import dagger.Provides

/**
 * @Author Centaury (alfa.arnialfa@gmail.com)
 * Created by Centaury on 7/27/2020.
 */
@Module
class DetailFavoriteModule {

    @Provides
    fun provideDetailFavoriteViewModel(detailFavoriteMovieViewModel: DetailFavoriteMovieViewModel): DetailFavoriteContract =
        detailFavoriteMovieViewModel
}