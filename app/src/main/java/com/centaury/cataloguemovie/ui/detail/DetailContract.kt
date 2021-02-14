package com.centaury.cataloguemovie.ui.detail

import com.centaury.domain.movies.model.MoviesDB
import com.centaury.domain.tvshow.model.TVShowsDB

/**
 * @Author Centaury (alfa.arnialfa@gmail.com)
 * Created by Centaury on 7/16/2020.
 */
interface DetailContract {

    fun getDetailMovieContract(movieId: Int)

    fun getDetailTVShowContract(tvShowId: Int)

    fun getFavoriteMovieByIdContract(movieId: Int)

    fun getInsertFavoriteMovieContract(movie: MoviesDB)

    fun getDeleteFavoriteMovieContract(movie: MoviesDB)

    fun getFavoriteTVShowByIdContract(tvShowId: Int)

    fun getInsertFavoriteTVSHowContract(tvShow: TVShowsDB)

    fun getDeleteFavoriteTVShowContract(tvShow: TVShowsDB)
}