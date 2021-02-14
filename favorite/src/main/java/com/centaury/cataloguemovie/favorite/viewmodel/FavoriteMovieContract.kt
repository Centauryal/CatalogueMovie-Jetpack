package com.centaury.cataloguemovie.favorite.viewmodel

import com.centaury.domain.movies.model.MoviesDB

/**
 * @Author Centaury (alfa.arnialfa@gmail.com)
 * Created by Centaury on 7/27/2020.
 */
interface FavoriteMovieContract {

    fun getAllFavoriteMovieContract()

    fun getFavoriteMovieByIdContract(movieId: Int)

    fun getDeleteFavoriteMovieContract(movie: MoviesDB)
}