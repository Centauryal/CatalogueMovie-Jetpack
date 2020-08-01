package com.centaury.cataloguemovie.ui.favorite.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.centaury.cataloguemovie.di.MovieScope
import com.centaury.cataloguemovie.viewmodel.ViewModelKey
import com.centaury.cataloguemovie.viewmodel.ViewModelProviderFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * @Author Centaury (alfa.arnialfa@gmail.com)
 * Created by Centaury on 7/27/2020.
 */
@Module
abstract class FavoriteMovieViewModelModule {

    @MovieScope
    @Binds
    internal abstract fun bindViewModelFactory(viewModelProviderFactory: ViewModelProviderFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(FavoriteMovieViewModel::class)
    internal abstract fun bindViewModel(viewModel: FavoriteMovieViewModel): ViewModel
}