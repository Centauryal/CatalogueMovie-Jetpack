package com.centaury.cataloguemovie.di.component

import com.centaury.cataloguemovie.di.MovieScope
import com.centaury.cataloguemovie.di.module.DetailModule
import com.centaury.cataloguemovie.ui.detail.DetailMovieActivity
import com.centaury.cataloguemovie.ui.detail.DetailMovieViewModelModule
import dagger.Component

/**
 * @Author Centaury (alfa.arnialfa@gmail.com)
 * Created by Centaury on 7/16/2020.
 */
@MovieScope
@Component(
    dependencies = [AppComponent::class],
    modules = [DetailModule::class, DetailMovieViewModelModule::class]
)
interface DetailComponent {
    fun inject(detail: DetailMovieActivity)
}