package com.centaury.cataloguemovie.data.remote

import com.centaury.cataloguemovie.BuildConfig

/**
 * Created by Centaury on 10/24/2019.
 */
object ApiEndPoint {
    const val ENDPOINT_DISCOVER_MOVIE = BuildConfig.BASE_URL + "discover/movie"
    const val ENDPOINT_DETAIL_MOVIE = BuildConfig.BASE_URL + "movie/{movie_id}"
    const val ENDPOINT_GENRE_MOVIE = BuildConfig.BASE_URL + "genre/movie/list"
    const val ENDPOINT_DISCOVER_TV_SHOW = BuildConfig.BASE_URL + "discover/tv"
    const val ENDPOINT_DETAIL_TV_SHOW = BuildConfig.BASE_URL + "tv/{tv_id}"
    const val ENDPOINT_GENRE_TV_SHOW = BuildConfig.BASE_URL + "genre/tv/list"
}