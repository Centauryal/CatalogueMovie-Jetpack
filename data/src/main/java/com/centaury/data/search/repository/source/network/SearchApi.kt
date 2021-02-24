package com.centaury.data.search.repository.source.network

import com.centaury.data.search.repository.source.network.result.SearchMovieResponse
import com.centaury.data.search.repository.source.network.result.SearchTVShowResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @Author Centaury (alfa.arnialfa@gmail.com)
 * Created by Centaury on 2/17/21.
 */
interface SearchApi {

    @GET("search/movie")
    fun searchMoviesAll(@Query("query") query: String): Observable<SearchMovieResponse>

    @GET("search/tv")
    fun searchTVShowsAll(@Query("query") query: String): Observable<SearchTVShowResponse>
}