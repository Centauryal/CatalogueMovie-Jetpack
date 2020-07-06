package com.centaury.cataloguemovie.data.remote

import com.centaury.cataloguemovie.data.remote.detail.tvshow.DetailTVShowResponse
import com.centaury.cataloguemovie.data.remote.genre.GenresItem
import com.centaury.data.movies.repository.source.network.result.detail.DetailMovieResponse

/**
 * Created by Centaury on 6/30/2020.
 */
class GetCallback {
    interface GetMovieDetailCallback {
        fun onResponse(detailMovieResponse: DetailMovieResponse?)
        fun onErrorResponse(message: String?)
    }

    interface GetGenreCallback {
        fun onResponse(genresItemList: List<GenresItem?>?)
        fun onErrorResponse(message: String?)
    }

    interface GetTVShowDetailCallback {
        fun onResponse(detailTVShowResponse: DetailTVShowResponse?)
        fun onErrorResponse(message: String?)
    }
}