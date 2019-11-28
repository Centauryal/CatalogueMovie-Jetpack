package com.centaury.cataloguemovie;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.centaury.cataloguemovie.data.CatalogueRepository;
import com.centaury.cataloguemovie.di.Injection;
import com.centaury.cataloguemovie.ui.detail.DetailFavoriteMovieViewModel;
import com.centaury.cataloguemovie.ui.detail.DetailMovieViewModel;
import com.centaury.cataloguemovie.ui.favorite.viewmodel.FavoriteMovieViewModel;
import com.centaury.cataloguemovie.ui.favorite.viewmodel.FavoriteTVShowViewModel;
import com.centaury.cataloguemovie.ui.movie.MovieViewModel;
import com.centaury.cataloguemovie.ui.tvshow.TVShowViewModel;

/**
 * Created by Centaury on 10/25/2019.
 */
public class ViewModelProviderFactory extends ViewModelProvider.NewInstanceFactory {

    private static volatile ViewModelProviderFactory INSTANCE;
    private final CatalogueRepository catalogueRepository;

    public ViewModelProviderFactory(CatalogueRepository catalogueRepository) {
        this.catalogueRepository = catalogueRepository;
    }

    public static ViewModelProviderFactory getInstance(Application application) {
        if (INSTANCE == null) {
            synchronized (ViewModelProviderFactory.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ViewModelProviderFactory(Injection.catalogueRepository(application));
                }
            }
        }

        return INSTANCE;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(MovieViewModel.class)) {
            //noinspection unchecked
            return (T) new MovieViewModel(catalogueRepository);
        } else if (modelClass.isAssignableFrom(TVShowViewModel.class)) {
            //noinspection unchecked
            return (T) new TVShowViewModel(catalogueRepository);
        } else if (modelClass.isAssignableFrom(DetailMovieViewModel.class)) {
            //noinspection unchecked
            return (T) new DetailMovieViewModel(catalogueRepository);
        } else if (modelClass.isAssignableFrom(FavoriteMovieViewModel.class)) {
            //noinspection unchecked
            return (T) new FavoriteMovieViewModel(catalogueRepository);
        } else if (modelClass.isAssignableFrom(FavoriteTVShowViewModel.class)) {
            //noinspection unchecked
            return (T) new FavoriteTVShowViewModel(catalogueRepository);
        } else if (modelClass.isAssignableFrom(DetailFavoriteMovieViewModel.class)) {
            //noinspection unchecked
            return (T) new DetailFavoriteMovieViewModel(catalogueRepository);
        }

        throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
    }
}
