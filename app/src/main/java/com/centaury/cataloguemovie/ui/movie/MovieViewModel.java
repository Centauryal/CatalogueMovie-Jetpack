package com.centaury.cataloguemovie.ui.movie;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.centaury.cataloguemovie.data.CatalogueRepository;
import com.centaury.cataloguemovie.data.datasource.MovieDataSource;
import com.centaury.cataloguemovie.data.datasource.MovieDataSourceFactory;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.inject.Inject;

/**
 * Created by Centaury on 10/7/2019.
 */
public class MovieViewModel extends ViewModel {

    private CatalogueRepository catalogueRepository;
    private LiveData<PagedList<MovieResultsItem>> movies;
    private LiveData<Boolean> loadingState;
    private LiveData<Boolean> loadMoreLoadingState;

    @Inject
    public MovieViewModel(CatalogueRepository catalogueRepository) {
        this.catalogueRepository = catalogueRepository;
        init();
    }

    private void init() {
        Executor executor = Executors.newFixedThreadPool(5);
        MovieDataSource movieDataSource = new MovieDataSource();
        MovieDataSourceFactory movieDataSourceFactory = new MovieDataSourceFactory(movieDataSource);

        PagedList.Config pagedListConfig =
                (new PagedList.Config.Builder())
                        .setEnablePlaceholders(true)
                        .setInitialLoadSizeHint(1)
                        .setPrefetchDistance(10)
                        .setPageSize(10).build();

        movies = (new LivePagedListBuilder<>(movieDataSourceFactory, pagedListConfig))
                .setFetchExecutor(executor)
                .build();

        loadingState = movieDataSource.getLoadingState();
        loadMoreLoadingState = movieDataSource.getLoadMoreLoadingState();
    }

    LiveData<PagedList<MovieResultsItem>> getMovies() {
        return movies;
    }

    LiveData<List<GenresItem>> getGenreMovie(String language) {
        return catalogueRepository.getGenreMovie(language);
    }

    LiveData<Boolean> getLoadingState() {
        return loadingState;
    }

    LiveData<Boolean> getLoadMoreLoadingState() {
        return loadMoreLoadingState;
    }
}
