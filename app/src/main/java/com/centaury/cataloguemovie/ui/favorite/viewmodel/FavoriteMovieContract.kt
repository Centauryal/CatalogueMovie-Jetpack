package com.centaury.cataloguemovie.ui.favorite.viewmodel

import android.content.Context
import com.centaury.domain.movies.model.MoviesEntity

/**
 * @Author Centaury (alfa.arnialfa@gmail.com)
 * Created by Centaury on 7/27/2020.
 */
interface FavoriteMovieContract {

    fun getAllFavoriteMovieContract()

    fun getFavoriteMovieByIdContract(movieId: Int)

    fun getDeleteFavoriteMovieContract(context: Context, movie: MoviesEntity)
}