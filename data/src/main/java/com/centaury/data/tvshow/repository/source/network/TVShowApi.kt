package com.centaury.data.tvshow.repository.source.network

import com.centaury.data.tvshow.repository.source.network.result.TVShowResponse
import io.reactivex.Observable
import retrofit2.http.GET

/**
 * @Author Centaury (alfa.arnialfa@gmail.com)
 * Created by Centaury on 7/15/2020.
 */
interface TVShowApi {

    @GET("discover/tv")
    fun discoveryTVShows(): Observable<TVShowResponse>
}