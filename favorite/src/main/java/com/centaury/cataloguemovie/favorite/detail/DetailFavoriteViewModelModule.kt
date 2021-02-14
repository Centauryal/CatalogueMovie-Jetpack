package com.centaury.cataloguemovie.favorite.detail

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
 * Created by Centaury on 2/12/2021.
 */
@Module
abstract class DetailFavoriteViewModelModule {

    @MovieScope
    @Binds
    internal abstract fun bindViewModelFactory(viewModelProviderFactory: ViewModelProviderFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(DetailFavoriteMovieViewModel::class)
    internal abstract fun bindFavoriteViewModel(favoriteViewModel: DetailFavoriteMovieViewModel): ViewModel
}