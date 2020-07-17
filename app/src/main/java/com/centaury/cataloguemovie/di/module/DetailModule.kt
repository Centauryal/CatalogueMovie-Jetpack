package com.centaury.cataloguemovie.di.module

import com.centaury.cataloguemovie.ui.detail.DetailContract
import com.centaury.cataloguemovie.ui.detail.DetailMovieViewModel
import dagger.Module
import dagger.Provides

/**
 * @Author Centaury (alfa.arnialfa@gmail.com)
 * Created by Centaury on 7/16/2020.
 */
@Module
class DetailModule {

    @Provides
    fun provideDetailViewModel(detailMovieViewModel: DetailMovieViewModel): DetailContract =
        detailMovieViewModel
}