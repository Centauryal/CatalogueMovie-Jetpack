package com.centaury.cataloguemovie.data.datasource;

import androidx.annotation.NonNull;
import androidx.paging.DataSource;

import com.centaury.cataloguemovie.data.remote.tvshow.TVShowResultsItem;

/**
 * Created by Centaury on 11/28/2019.
 */
public class TVShowDataSourceFactory extends DataSource.Factory<Integer, TVShowResultsItem> {

    private TVShowDataSource tvShowDataSource;

    public TVShowDataSourceFactory(TVShowDataSource tvShowDataSource) {
        this.tvShowDataSource = tvShowDataSource;
    }

    @NonNull
    @Override
    public DataSource<Integer, TVShowResultsItem> create() {
        return tvShowDataSource;
    }
}
