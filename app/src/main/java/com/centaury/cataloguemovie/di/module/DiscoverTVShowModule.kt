package com.centaury.cataloguemovie.di.module

import com.centaury.cataloguemovie.ui.tvshow.TVShowContract
import com.centaury.cataloguemovie.ui.tvshow.TVShowViewModel
import dagger.Module
import dagger.Provides

/**
 * @Author Centaury (alfa.arnialfa@gmail.com)
 * Created by Centaury on 7/15/2020.
 */
@Module
class DiscoverTVShowModule {

    @Provides
    fun provideTVShowViewModel(tvShowViewModel: TVShowViewModel): TVShowContract = tvShowViewModel
}