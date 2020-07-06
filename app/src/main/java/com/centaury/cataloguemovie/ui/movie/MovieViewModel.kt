package com.centaury.cataloguemovie.ui.movie

import androidx.lifecycle.ViewModel
import com.centaury.domain.UseCase
import com.centaury.domain.movies.interactor.GetDiscoveryMovie
import javax.inject.Inject

/**
 * Created by Centaury on 10/7/2019.
 */
class MovieViewModel @Inject constructor(
    private val getDiscoveryMovie: GetDiscoveryMovie,
    private val view: MovieContract.View
) : ViewModel(), MovieContract {

    init {
        getDiscoverMovieContract()
    }

    override fun getDiscoverMovieContract() {
        getDiscoveryMovie.execute(UseCase.None(), onSuccess = {
            view.onGetNowPlayingSuccess(it)
        }, onError = {
            view.onGetNowPlayingFailed("Something went wrong")
        })
    }
}