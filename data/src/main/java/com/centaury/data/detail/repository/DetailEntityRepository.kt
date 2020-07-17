package com.centaury.data.detail.repository

import com.centaury.data.common.Source
import com.centaury.data.detail.mapper.DetailResultMapper
import com.centaury.data.detail.repository.source.DetailDataFactory
import com.centaury.domain.detail.DetailRepository
import com.centaury.domain.detail.model.Detail
import io.reactivex.Observable
import javax.inject.Inject

/**
 * @Author Centaury (alfa.arnialfa@gmail.com)
 * Created by Centaury on 7/16/2020.
 */
class DetailEntityRepository @Inject constructor(
    private val detailDataFactory: DetailDataFactory,
    private val detailResultMapper: DetailResultMapper
) : DetailRepository {

    override fun getDetailMovie(movieId: Int): Observable<Detail> =
        createDetailData().detailMovie(movieId).map { detailResultMapper.transformDetailMovie(it) }

    override fun getDetailTVShow(tvShowId: Int): Observable<Detail> =
        createDetailData().detailTVShow(tvShowId)
            .map { detailResultMapper.transformDetailTVShow(it) }

    private fun createDetailData(): DetailEntityData =
        detailDataFactory.createData(Source.NETWORK)

}