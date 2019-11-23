package com.centaury.cataloguemovie.ui.detail;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.centaury.cataloguemovie.data.CatalogueRepository;
import com.centaury.cataloguemovie.data.local.entity.MovieEntity;
import com.centaury.cataloguemovie.data.local.entity.TVShowEntity;
import com.centaury.cataloguemovie.vo.Resource;

/**
 * Created by Centaury on 10/7/2019.
 */
public class DetailMovieViewModel extends ViewModel {

    private CatalogueRepository catalogueRepository;
    private String movieId;
    private String tvshowId;
    private String language;

    public DetailMovieViewModel(CatalogueRepository catalogueRepository) {
        this.catalogueRepository = catalogueRepository;
    }

    LiveData<Resource<MovieEntity>> getDetailMovie() {
        return catalogueRepository.getDetailMovie(movieId, language);
    }

    LiveData<Resource<TVShowEntity>> getDetailTVShow() {
        return catalogueRepository.getDetailTVShow(tvshowId, language);
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public void setTvshowId(String tvshowId) {
        this.tvshowId = tvshowId;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    void setFavoriteMovie() {
        if (getDetailMovie().getValue() != null) {
            MovieEntity movieEntity = getDetailMovie().getValue().data;

            if (movieEntity != null) {
                final boolean newState = !movieEntity.isMovieFavorited();
                catalogueRepository.setFavoriteMovie(movieEntity, newState);
            }
        }
    }

    void setFavoriteTVShow() {
        if (getDetailTVShow().getValue() != null) {
            TVShowEntity tvShowEntity = getDetailTVShow().getValue().data;

            if (tvShowEntity != null) {
                final boolean newState = !tvShowEntity.isTvshowFavorited();
                catalogueRepository.setFavoriteTVShow(tvShowEntity, newState);
            }
        }
    }
}
