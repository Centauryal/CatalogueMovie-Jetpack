package com.centaury.cataloguemovie.ui.detail

import com.centaury.domain.movies.model.MoviesEntity
import com.centaury.domain.tvshow.model.TVShowsEntity

/**
 * @Author Centaury (alfa.arnialfa@gmail.com)
 * Created by Centaury on 7/16/2020.
 */
interface DetailContract {

    fun getDetailMovieContract(movieId: Int)

    fun getDetailTVShowContract(tvShowId: Int)

    fun getFavoriteMovieByIdContract(movieId: Int)

    fun getInsertFavoriteMovieContract(movie: MoviesEntity)

    fun getDeleteFavoriteMovieContract(movie: MoviesEntity)

    fun getFavoriteTVShowByIdContract(tvShowId: Int)

    fun getInsertFavoriteTVSHowContract(tvShow: TVShowsEntity)

    fun getDeleteFavoriteTVShowContract(tvShow: TVShowsEntity)
}