package com.centaury.data.tvshow.repository.source.network

import com.centaury.data.tvshow.repository.source.network.result.DetailTVShowResponse
import com.centaury.data.tvshow.repository.source.network.result.GenreTVShowResponse
import com.centaury.data.tvshow.repository.source.network.result.SearchTVShowResponse
import com.centaury.data.tvshow.repository.source.network.result.TVShowResponse
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * @Author Centaury (alfa.arnialfa@gmail.com)
 * Created by Centaury on 7/15/2020.
 */
interface TVShowApi {

    @Headers("Cache-Control: max-age=64800")
    @GET("discover/tv")
    suspend fun discoveryTVShows(
        @Query("page") page: Int = 1
    ): TVShowResponse

    @GET("tv/{tv_id}")
    suspend fun detailTVShow(@Path("tv_id") tvShowId: Int): DetailTVShowResponse

    @GET("genre/tv/list")
    suspend fun genreTVShows(): GenreTVShowResponse

    @GET("search/tv")
    suspend fun searchTVShowsAll(
        @Query("query") query: String,
        @Query("page") page: Int = 1
    ): SearchTVShowResponse
}
