package com.centaury.cataloguemovie.ui.detail;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.centaury.cataloguemovie.data.CatalogueRepository;
import com.centaury.cataloguemovie.data.local.entity.GenreMovieEntity;
import com.centaury.cataloguemovie.data.local.entity.GenreTVShowEntity;
import com.centaury.cataloguemovie.data.local.entity.MovieEntity;
import com.centaury.cataloguemovie.data.local.entity.TVShowEntity;
import com.centaury.cataloguemovie.vo.Resource;

import java.util.List;

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

    LiveData<Resource<List<GenreMovieEntity>>> getGenreMovie() {
        return catalogueRepository.getGenreMovie(language);
    }

    LiveData<Resource<TVShowEntity>> getDetailTVShow() {
        return catalogueRepository.getDetailTVShow(tvshowId, language);
    }

    LiveData<Resource<List<GenreTVShowEntity>>> getGenreTVShow() {
        return catalogueRepository.getGenreTVShow(language);
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

    void setFavoriteMovie(MovieEntity movieEntity, boolean newState) {
        catalogueRepository.setFavoriteMovie(movieEntity, newState);
    }

    void setFavoriteTVShow(TVShowEntity tvShowEntity, boolean newState) {
        catalogueRepository.setFavoriteTVShow(tvShowEntity, newState);
    }
}
