package com.centaury.cataloguemovie.data.remote

import com.centaury.cataloguemovie.data.remote.GetCallback.*

/**
 * Created by Centaury on 7/1/2020.
 */
interface RemoteDataSource {

    fun getMovieDetail(movieId: String, language: String, callback: GetMovieDetailCallback)
    fun getGenreMovie(language: String, callback: GetGenreCallback)
    fun getTVShowDetail(tvShowId: String, language: String, callback: GetTVShowDetailCallback)
    fun getGenreTVShow(language: String, callback: GetGenreCallback)
}