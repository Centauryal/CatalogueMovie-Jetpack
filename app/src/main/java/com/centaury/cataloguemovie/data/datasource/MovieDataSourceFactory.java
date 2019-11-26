package com.centaury.cataloguemovie.data.datasource;

import androidx.annotation.NonNull;
import androidx.paging.DataSource;

/**
 * Created by Centaury on 11/27/2019.
 */
public class MovieDataSourceFactory extends DataSource.Factory {

    private MovieDataSource movieDataSource;

    @NonNull
    @Override
    public DataSource create() {
        return movieDataSource;
    }
}
