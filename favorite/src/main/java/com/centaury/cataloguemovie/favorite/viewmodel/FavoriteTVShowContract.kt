package com.centaury.cataloguemovie.favorite.viewmodel

import com.centaury.domain.model.TVShowsDB

/**
 * @Author Centaury (alfa.arnialfa@gmail.com)
 * Created by Centaury on 7/27/2020.
 */
interface FavoriteTVShowContract {

    fun getAllFavoriteTVShowContract()

    fun getFavoriteTVShowByIdContract(tvShowId: Int)

    fun getDeleteFavoriteTVShowContract(tvShow: TVShowsDB)
}