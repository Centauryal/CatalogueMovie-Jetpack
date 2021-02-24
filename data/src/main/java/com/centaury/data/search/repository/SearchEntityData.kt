package com.centaury.data.search.repository

import com.centaury.data.search.repository.source.network.result.SearchMovieResponse
import com.centaury.data.search.repository.source.network.result.SearchTVShowResponse
import io.reactivex.Observable

/**
 * @Author Centaury (alfa.arnialfa@gmail.com)
 * Created by Centaury on 2/17/21.
 */
interface SearchEntityData {

    fun searchMoviesAll(query: String): Observable<SearchMovieResponse>

    fun searchTVShowsAll(query: String): Observable<SearchTVShowResponse>
}