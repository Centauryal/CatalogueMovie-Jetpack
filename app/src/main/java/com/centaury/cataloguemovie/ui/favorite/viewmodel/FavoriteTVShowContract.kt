package com.centaury.cataloguemovie.ui.favorite.viewmodel

import com.centaury.domain.tvshow.model.TVShowsEntity

/**
 * @Author Centaury (alfa.arnialfa@gmail.com)
 * Created by Centaury on 7/27/2020.
 */
interface FavoriteTVShowContract {

    fun getAllFavoriteTVShowContract()

    fun getFavoriteTVShowByIdContract(tvShowId: Int)

    fun getDeleteFavoriteTVShowContract(tvShow: TVShowsEntity)
}