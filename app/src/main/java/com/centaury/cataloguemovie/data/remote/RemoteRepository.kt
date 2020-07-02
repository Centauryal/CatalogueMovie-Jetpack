package com.centaury.cataloguemovie.data.remote

import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.centaury.cataloguemovie.data.remote.GetCallback.*
import com.google.gson.Gson

/**
 * Created by Centaury on 10/24/2019.
 */
class RemoteRepository : RemoteDataSource {

    override fun getMovieDetail(
        movieId: String,
        language: String,
        callback: GetMovieDetailCallback
    ) {
    }

    override fun getGenreMovie(language: String, callback: GetGenreCallback) {
    }

    override fun getTVShowDetail(
        tvShowId: String,
        language: String,
        callback: GetTVShowDetailCallback
    ) {
    }

    override fun getGenreTVShow(language: String, callback: GetGenreCallback) {
    }

}