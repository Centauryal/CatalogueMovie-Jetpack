package com.centaury.cataloguemovie.ui.detail

/**
 * @Author Centaury (alfa.arnialfa@gmail.com)
 * Created by Centaury on 7/16/2020.
 */
interface DetailContract {

    fun getDetailMovieContract(movieId: Int)

    fun getDetailTVShowContract(tvShowId: Int)
}