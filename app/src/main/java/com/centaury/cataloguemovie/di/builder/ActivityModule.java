package com.centaury.cataloguemovie.di.builder;

import com.centaury.cataloguemovie.ui.detail.DetailFavoriteMovieActivity;
import com.centaury.cataloguemovie.ui.detail.DetailMovieActivity;
import com.centaury.cataloguemovie.ui.favorite.FavoriteActivity;
import com.centaury.cataloguemovie.ui.main.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by Centaury on 1/17/2020.
 */
@Module
public abstract class ActivityModule {

    @ContributesAndroidInjector(modules = FragmentBuildersModule.class)
    abstract MainActivity contributeMainActivity();

    @ContributesAndroidInjector(modules = FragmentBuildersModule.class)
    abstract FavoriteActivity contributeFavoriteActivity();

    @ContributesAndroidInjector(modules = FragmentBuildersModule.class)
    abstract DetailMovieActivity contributeDetailMovieActivity();

    @ContributesAndroidInjector(modules = FragmentBuildersModule.class)
    abstract DetailFavoriteMovieActivity contributeDetailFavoriteMovieActivity();
}
