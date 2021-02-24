package com.centaury.data.search.repository.source.local

import com.centaury.data.search.repository.SearchEntityData
import com.centaury.data.search.repository.source.network.result.SearchMovieResponse
import com.centaury.data.search.repository.source.network.result.SearchTVShowResponse
import io.reactivex.Observable
import javax.inject.Inject

/**
 * @Author Centaury (alfa.arnialfa@gmail.com)
 * Created by Centaury on 2/17/21.
 */
class LocalSearchEntityData @Inject constructor() : SearchEntityData {

    override fun searchMoviesAll(query: String): Observable<SearchMovieResponse> {
        TODO("Not yet implemented")
    }

    override fun searchTVShowsAll(query: String): Observable<SearchTVShowResponse> {
        TODO("Not yet implemented")
    }

}