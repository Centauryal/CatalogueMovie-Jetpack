package com.centaury.cataloguemovie.ui.movie

import com.centaury.domain.movies.model.Movie

/**
 * @Author Centaury (alfa.arnialfa@gmail.com)
 * Created by Centaury on 7/6/2020.
 */
interface MovieContract {
    fun getDiscoverMovieContract()

    interface View {
        fun onGetNowPlayingSuccess(movies: List<Movie>)
        fun onGetNowPlayingFailed(message: String)
    }
}