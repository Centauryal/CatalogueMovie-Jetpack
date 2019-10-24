package com.centaury.cataloguemovie.ui.movie;

import androidx.lifecycle.ViewModel;

import com.centaury.cataloguemovie.data.local.entity.MovieEntity;
import com.centaury.cataloguemovie.utils.DataDummy;

import java.util.List;

/**
 * Created by Centaury on 10/7/2019.
 */
public class MovieViewModel extends ViewModel {

    public List<MovieEntity> getMovies() {
        return DataDummy.generateDummyMovies();
    }
}
