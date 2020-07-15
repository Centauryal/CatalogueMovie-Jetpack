package com.centaury.data.tvshow.repository

import com.centaury.data.common.Source
import com.centaury.data.tvshow.mapper.TVShowResultMapper
import com.centaury.data.tvshow.repository.source.TVShowsDataFactory
import com.centaury.domain.movies.model.Detail
import com.centaury.domain.tvshow.TVShowsRepository
import com.centaury.domain.tvshow.model.TVShow
import io.reactivex.Observable
import javax.inject.Inject

/**
 * @Author Centaury (alfa.arnialfa@gmail.com)
 * Created by Centaury on 7/15/2020.
 */
class TVShowsEntityRepository @Inject constructor(
    private val tvShowsDataFactory: TVShowsDataFactory,
    private val tvShowResultMapper: TVShowResultMapper
) : TVShowsRepository {

    override fun getDiscoveryTVShows(): Observable<List<TVShow>> =
        createTVShowData().discoveryTVShows().map { tvShowResultMapper.transformTVShow(it) }

    override fun getDetailTVShow(tvShowId: String): Observable<Detail> {
        TODO("Not yet implemented")
    }

    private fun createTVShowData(): TVShowsEntityData =
        tvShowsDataFactory.createData(Source.NETWORK)
}