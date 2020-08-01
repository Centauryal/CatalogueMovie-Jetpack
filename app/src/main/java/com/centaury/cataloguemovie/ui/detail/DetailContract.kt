package com.centaury.cataloguemovie.ui.detail

import android.content.Context
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

    fun getInsertFavoriteMovieContract(context: Context, movie: MoviesEntity)

    fun getDeleteFavoriteMovieContract(context: Context, movie: MoviesEntity)

    fun getFavoriteTVShowByIdContract(tvShowId: Int)

    fun getInsertFavoriteTVSHowContract(context: Context, tvShow: TVShowsEntity)

    fun getDeleteFavoriteTVShowContract(context: Context, tvShow: TVShowsEntity)
}