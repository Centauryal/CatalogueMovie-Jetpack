package com.centaury.cataloguemovie.data.remote

import com.centaury.cataloguemovie.data.remote.detail.movie.DetailMovieResponse
import com.centaury.cataloguemovie.data.remote.detail.tvshow.DetailTVShowResponse
import com.centaury.cataloguemovie.data.remote.genre.GenreResponse
import com.centaury.cataloguemovie.data.remote.movie.MovieResponse
import com.centaury.cataloguemovie.data.remote.tvshow.TVShowResponse
import retrofit2.Call
import retrofit2.http.GET

/**
 * Created by Centaury on 7/1/2020.
 */
interface ApiInterface {

    @GET(ApiEndPoint.ENDPOINT_DISCOVER_MOVIE)
    fun getMovies(): Call<MovieResponse>

    @GET(ApiEndPoint.ENDPOINT_DETAIL_MOVIE)
    fun getMovieDetail(): Call<DetailMovieResponse>

    @GET(ApiEndPoint.ENDPOINT_GENRE_MOVIE)
    fun getMovieGenres(): Call<GenreResponse>

    @GET(ApiEndPoint.ENDPOINT_DISCOVER_TV_SHOW)
    fun getTVShows(): Call<TVShowResponse>

    @GET(ApiEndPoint.ENDPOINT_DETAIL_TV_SHOW)
    fun getTVShowDetail(): Call<DetailTVShowResponse>

    @GET(ApiEndPoint.ENDPOINT_GENRE_TV_SHOW)
    fun getTVShowGenres(): Call<GenreResponse>
}