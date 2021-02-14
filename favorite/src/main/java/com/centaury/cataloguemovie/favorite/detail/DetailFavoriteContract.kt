package com.centaury.cataloguemovie.favorite.detail

import com.centaury.domain.movies.model.MoviesDB
import com.centaury.domain.tvshow.model.TVShowsDB

/**
 * @Author Centaury (alfa.arnialfa@gmail.com)
 * Created by Centaury on 7/27/2020.
 */
interface DetailFavoriteContract {

    fun getFavoriteMovieByIdContract(movieId: Int)

    fun getInsertFavoriteMovieContract(movie: MoviesDB)

    fun getDeleteFavoriteMovieContract(movie: MoviesDB)

    fun getFavoriteTVShowByIdContract(tvShowId: Int)

    fun getInsertFavoriteTVSHowContract(tvShow: TVShowsDB)

    fun getDeleteFavoriteTVShowContract(tvShow: TVShowsDB)
}