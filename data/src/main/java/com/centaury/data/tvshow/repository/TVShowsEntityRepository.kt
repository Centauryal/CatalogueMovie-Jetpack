package com.centaury.data.tvshow.repository

import com.centaury.data.common.Source
import com.centaury.data.tvshow.mapper.TVShowEntityMapper
import com.centaury.data.tvshow.mapper.TVShowResultMapper
import com.centaury.data.tvshow.repository.source.TVShowsDataFactory
import com.centaury.domain.model.*
import com.centaury.domain.tvshow.TVShowsRepository
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import javax.inject.Inject

/**
 * @Author Centaury (alfa.arnialfa@gmail.com)
 * Created by Centaury on 7/15/2020.
 */
class TVShowsEntityRepository @Inject constructor(
    private val tvShowsDataFactory: TVShowsDataFactory,
    private val tvShowResultMapper: TVShowResultMapper,
    private val tvShowEntityMapper: TVShowEntityMapper
) : TVShowsRepository {

    override fun getDiscoveryTVShows(): Observable<List<TVShow>> =
        createTVShowData().discoveryTVShows().map { tvShowResultMapper.transformTVShow(it) }

    override fun getDetailTVShow(tvShowId: Int): Observable<Detail> =
        createTVShowData().detailTVShow(tvShowId)
            .map { tvShowResultMapper.transformDetailTVShow(it) }

    override fun getGenreTVShows(): Observable<List<Genre>> =
        createTVShowData().genreTVShows().map { tvShowResultMapper.transformGenreTVShow(it) }

    override fun getSearchTVShowsAll(query: String): Observable<List<Search>> =
        createTVShowData().searchTVShowsAll(query)
            .map { tvShowResultMapper.transformSearchTVShow(it) }

    override fun getAllFavoriteTVShow(): Flowable<List<TVShowsDB>> =
        createEntityTVShowData().getAllFavoriteTVShow()
            .map { tvShowEntityMapper.transformEntityTVShow(it) }

    override fun getFavoriteTVShowById(id: Int): Flowable<TVShowsDB> =
        createEntityTVShowData().getFavoriteTVShowById(id)
            .map { tvShowEntityMapper.transformEntityTVShowById(it) }

    override fun insertFavoriteTVShow(tvShow: TVShowsDB): Completable =
        createEntityTVShowData().insertFavoriteTVShow(
            tvShowEntityMapper.transformTVShowToEntity(tvShow)
        )

    override fun deleteFavoriteTVShow(tvShow: TVShowsDB): Completable =
        createEntityTVShowData().deleteFavoriteTVShow(
            tvShowEntityMapper.transformTVShowToEntity(tvShow)
        )

    private fun createTVShowData(): TVShowsEntityData =
        tvShowsDataFactory.createData(Source.NETWORK)

    private fun createEntityTVShowData(): TVShowsEntityData =
        tvShowsDataFactory.createData(Source.LOCAL)
}