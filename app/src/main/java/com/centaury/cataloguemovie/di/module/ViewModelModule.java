package com.centaury.cataloguemovie.di.module;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.centaury.cataloguemovie.ViewModelProviderFactory;
import com.centaury.cataloguemovie.di.ViewModelKey;
import com.centaury.cataloguemovie.ui.detail.DetailFavoriteMovieViewModel;
import com.centaury.cataloguemovie.ui.detail.DetailMovieViewModel;
import com.centaury.cataloguemovie.ui.favorite.viewmodel.FavoriteMovieViewModel;
import com.centaury.cataloguemovie.ui.favorite.viewmodel.FavoriteTVShowViewModel;
import com.centaury.cataloguemovie.ui.movie.MovieViewModel;
import com.centaury.cataloguemovie.ui.tvshow.TVShowViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

/**
 * Created by Centaury on 1/17/2020.
 */
@Module
public abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(MovieViewModel.class)
    abstract ViewModel bindMovieViewModel(MovieViewModel movieViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(TVShowViewModel.class)
    abstract ViewModel bindTVShowViewModel(TVShowViewModel tvShowViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(DetailMovieViewModel.class)
    abstract ViewModel bindDetailMovieViewModel(DetailMovieViewModel detailMovieViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(DetailFavoriteMovieViewModel.class)
    abstract ViewModel bindDetailFavoriteMovieViewModel(DetailFavoriteMovieViewModel detailFavoriteMovieViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(FavoriteMovieViewModel.class)
    abstract ViewModel bindFavoriteMovieViewModel(FavoriteMovieViewModel favoriteMovieViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(FavoriteTVShowViewModel.class)
    abstract ViewModel bindFavoriteTVShowViewModel(FavoriteTVShowViewModel favoriteTVShowViewModel);

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelProviderFactory providerFactory);
}
