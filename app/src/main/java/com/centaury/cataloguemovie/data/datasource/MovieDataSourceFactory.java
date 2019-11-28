package com.centaury.cataloguemovie.data.datasource;

import androidx.annotation.NonNull;
import androidx.paging.DataSource;

import com.centaury.cataloguemovie.data.remote.movie.MovieResultsItem;

/**
 * Created by Centaury on 11/27/2019.
 */
public class MovieDataSourceFactory extends DataSource.Factory<Integer, MovieResultsItem> {

    private MovieDataSource movieDataSource;

    public MovieDataSourceFactory(MovieDataSource movieDataSource) {
        this.movieDataSource = movieDataSource;
    }

    @NonNull
    @Override
    public DataSource<Integer, MovieResultsItem> create() {
        return movieDataSource;
    }
}
