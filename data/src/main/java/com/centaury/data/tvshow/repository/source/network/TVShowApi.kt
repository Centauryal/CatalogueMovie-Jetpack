package com.centaury.data.tvshow.repository.source.network

import com.centaury.data.tvshow.repository.source.network.result.detail.DetailTVShowResponse
import com.centaury.data.tvshow.repository.source.network.result.tvshow.TVShowResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * @Author Centaury (alfa.arnialfa@gmail.com)
 * Created by Centaury on 7/15/2020.
 */
interface TVShowApi {

    @GET("discover/tv")
    fun discoveryTVShows(): Observable<TVShowResponse>

    @GET("tv/{tv_id}")
    fun detailTVShow(@Path("tv_id") tvShowId: String): Observable<DetailTVShowResponse>
}