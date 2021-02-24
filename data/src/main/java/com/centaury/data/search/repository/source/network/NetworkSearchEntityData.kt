package com.centaury.data.search.repository.source.network

import com.centaury.data.search.repository.SearchEntityData
import com.centaury.data.search.repository.source.network.result.SearchMovieResponse
import com.centaury.data.search.repository.source.network.result.SearchTVShowResponse
import io.reactivex.Observable
import javax.inject.Inject

/**
 * @Author Centaury (alfa.arnialfa@gmail.com)
 * Created by Centaury on 2/17/21.
 */
class NetworkSearchEntityData @Inject constructor(
    private val searchApi: SearchApi
) : SearchEntityData {

    override fun searchMoviesAll(query: String): Observable<SearchMovieResponse> =
        searchApi.searchMoviesAll(query)

    override fun searchTVShowsAll(query: String): Observable<SearchTVShowResponse> =
        searchApi.searchTVShowsAll(query)

}