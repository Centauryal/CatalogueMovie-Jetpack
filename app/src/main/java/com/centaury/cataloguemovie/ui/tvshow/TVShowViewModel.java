package com.centaury.cataloguemovie.ui.tvshow;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.centaury.cataloguemovie.data.CatalogueRepository;
import com.centaury.cataloguemovie.data.datasource.TVShowDataSource;
import com.centaury.cataloguemovie.data.datasource.TVShowDataSourceFactory;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.inject.Inject;

/**
 * Created by Centaury on 10/7/2019.
 */
public class TVShowViewModel extends ViewModel {

    private CatalogueRepository catalogueRepository;
    private LiveData<PagedList<TVShowResultsItem>> tvshows;
    private LiveData<Boolean> loadingState;
    private LiveData<Boolean> loadMoreLoadingState;

    @Inject
    public TVShowViewModel(CatalogueRepository catalogueRepository) {
        this.catalogueRepository = catalogueRepository;
        init();
    }

    private void init() {
        Executor executor = Executors.newFixedThreadPool(5);
        TVShowDataSource tvShowDataSource = new TVShowDataSource();
        TVShowDataSourceFactory tvShowDataSourceFactory = new TVShowDataSourceFactory(tvShowDataSource);

        PagedList.Config pagedListConfig =
                (new PagedList.Config.Builder())
                        .setEnablePlaceholders(true)
                        .setInitialLoadSizeHint(1)
                        .setPrefetchDistance(10)
                        .setPageSize(10).build();

        tvshows = (new LivePagedListBuilder<>(tvShowDataSourceFactory, pagedListConfig))
                .setFetchExecutor(executor)
                .build();

        loadingState = tvShowDataSource.getLoadingState();
        loadMoreLoadingState = tvShowDataSource.getLoadMoreLoadingState();
    }

    LiveData<PagedList<TVShowResultsItem>> getTVShows() {
        return tvshows;
    }

    LiveData<List<GenresItem>> getGenreTVShow(String language) {
        return catalogueRepository.getGenreTVShow(language);
    }

    LiveData<Boolean> getLoadingState() {
        return loadingState;
    }

    LiveData<Boolean> getLoadMoreLoadingState() {
        return loadMoreLoadingState;
    }
}
