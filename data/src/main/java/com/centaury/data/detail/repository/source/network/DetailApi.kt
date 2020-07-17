package com.centaury.data.detail.repository.source.network

import com.centaury.data.detail.repository.source.network.result.movie.DetailMovieResponse
import com.centaury.data.detail.repository.source.network.result.tvshow.DetailTVShowResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * @Author Centaury (alfa.arnialfa@gmail.com)
 * Created by Centaury on 7/16/2020.
 */
interface DetailApi {

    @GET("movie/{movie_id}")
    fun detailMovie(@Path("movie_id") movieId: Int): Observable<DetailMovieResponse>

    @GET("tv/{tv_id}")
    fun detailTVShow(@Path("tv_id") tvShowId: Int): Observable<DetailTVShowResponse>
}