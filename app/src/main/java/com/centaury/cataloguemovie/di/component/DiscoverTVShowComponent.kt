package com.centaury.cataloguemovie.di.component

import com.centaury.cataloguemovie.di.MovieScope
import com.centaury.cataloguemovie.di.module.DiscoverTVShowModule
import com.centaury.cataloguemovie.ui.tvshow.TVShowFragment
import com.centaury.cataloguemovie.ui.tvshow.TVShowViewModelModule
import dagger.Component

/**
 * @Author Centaury (alfa.arnialfa@gmail.com)
 * Created by Centaury on 7/15/2020.
 */
@MovieScope
@Component(
    dependencies = [AppComponent::class],
    modules = [DiscoverTVShowModule::class, TVShowViewModelModule::class]
)
interface DiscoverTVShowComponent {
    fun inject(discoverTVShow: TVShowFragment)
}