package com.centaury.domain.search

import com.centaury.domain.search.model.Search
import io.reactivex.Observable

/**
 * @Author Centaury (alfa.arnialfa@gmail.com)
 * Created by Centaury on 2/17/21.
 */
interface SearchRepository {

    fun getSearchMoviesAll(query: String): Observable<List<Search>>

    fun getSearchTVShowsAll(query: String): Observable<List<Search>>
}