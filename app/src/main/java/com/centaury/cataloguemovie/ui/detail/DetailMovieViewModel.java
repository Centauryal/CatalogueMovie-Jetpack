package com.centaury.cataloguemovie.ui.detail;

import androidx.lifecycle.ViewModel;

import com.centaury.cataloguemovie.data.MovieEntity;
import com.centaury.cataloguemovie.data.TVShowEntity;
import com.centaury.cataloguemovie.utils.DataDummy;

/**
 * Created by Centaury on 10/7/2019.
 */
public class DetailMovieViewModel extends ViewModel {

    private MovieEntity movieEntity;
    private TVShowEntity tvShowEntity;
    private Integer movieId;
    private Integer tvshowId;

    public MovieEntity getMovie() {
        for (int i = 0; i < DataDummy.generateDummyMovies().size(); i++) {
            MovieEntity entity = DataDummy.generateDummyMovies().get(i);
            if (entity.getMovieId() == movieId) {
                movieEntity = entity;
            }
        }

        return movieEntity;
    }

    public TVShowEntity getTvShow() {
        for (int i = 0; i < DataDummy.generateDummyTVShows().size(); i++) {
            TVShowEntity entity = DataDummy.generateDummyTVShows().get(i);
            if (entity.getTvshowId() == tvshowId) {
                tvShowEntity = entity;
            }
        }

        return tvShowEntity;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public int getTvshowId() {
        return tvshowId;
    }

    public void setTvshowId(int tvshowId) {
        this.tvshowId = tvshowId;
    }
}
