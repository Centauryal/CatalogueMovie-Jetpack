package com.centaury.cataloguemovie.di.builder;

import com.centaury.cataloguemovie.ui.favorite.fragment.FavoriteMovieFragment;
import com.centaury.cataloguemovie.ui.favorite.fragment.FavoriteTVShowFragment;
import com.centaury.cataloguemovie.ui.movie.MovieFragment;
import com.centaury.cataloguemovie.ui.tvshow.TVShowFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by Centaury on 1/17/2020.
 */
@Module
abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    abstract MovieFragment contributeMovieFragment();

    @ContributesAndroidInjector
    abstract TVShowFragment contributeTVShowFragment();

    @ContributesAndroidInjector
    abstract FavoriteMovieFragment contributeFavoriteMovieFragment();

    @ContributesAndroidInjector
    abstract FavoriteTVShowFragment contributeFavoriteTVShowFragment();
}
