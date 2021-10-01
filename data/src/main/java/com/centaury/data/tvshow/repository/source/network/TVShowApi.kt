package com.centaury.data.tvshow.repository.source.network

import com.centaury.data.tvshow.repository.source.network.result.DetailTVShowResponse
import com.centaury.data.tvshow.repository.source.network.result.GenreTVShowResponse
import com.centaury.data.tvshow.repository.source.network.result.SearchTVShowResponse
import com.centaury.data.tvshow.repository.source.network.result.TVShowResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * @Author Centaury (alfa.arnialfa@gmail.com)
 * Created by Centaury on 7/15/2020.
 */
interface TVShowApi {

    @GET("discover/tv")
    fun discoveryTVShows(): Observable<TVShowResponse>

    @GET("tv/{tv_id}")
    fun detailTVShow(@Path("tv_id") tvShowId: Int): Observable<DetailTVShowResponse>

    @GET("genre/tv/list")
    fun genreTVShows(): Observable<GenreTVShowResponse>

    @GET("search/tv")
    fun searchTVShowsAll(@Query("query") query: String): Observable<SearchTVShowResponse>
}