package com.centaury.data.search.repository

import com.centaury.data.common.Source
import com.centaury.data.search.mapper.SearchResultMapper
import com.centaury.data.search.repository.source.SearchDataFactory
import com.centaury.domain.search.SearchRepository
import com.centaury.domain.search.model.Search
import io.reactivex.Observable
import javax.inject.Inject

/**
 * @Author Centaury (alfa.arnialfa@gmail.com)
 * Created by Centaury on 2/17/21.
 */
class SearchEntityRepository @Inject constructor(
    private val searchDataFactory: SearchDataFactory,
    private val searchResultMapper: SearchResultMapper
) : SearchRepository {

    override fun getSearchMoviesAll(query: String): Observable<List<Search>> =
        createSearchData().searchMoviesAll(query)
            .map { searchResultMapper.transformSearchMovie(it) }

    override fun getSearchTVShowsAll(query: String): Observable<List<Search>> =
        createSearchData().searchTVShowsAll(query)
            .map { searchResultMapper.transformSearchTVShow(it) }

    private fun createSearchData(): SearchEntityData =
        searchDataFactory.createData(Source.NETWORK)

}