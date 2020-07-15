package com.centaury.data.tvshow.repository.source.network

import com.centaury.data.tvshow.repository.TVShowsEntityData
import com.centaury.data.tvshow.repository.source.network.result.detail.DetailTVShowResponse
import com.centaury.data.tvshow.repository.source.network.result.tvshow.TVShowResponse
import io.reactivex.Observable
import javax.inject.Inject

/**
 * @Author Centaury (alfa.arnialfa@gmail.com)
 * Created by Centaury on 7/15/2020.
 */
class NetworkTVShowsEntityData @Inject constructor(
    private val tvShowApi: TVShowApi
) : TVShowsEntityData {
    override fun discoveryTVShows(): Observable<TVShowResponse> = tvShowApi.discoveryTVShows()

    override fun detailTVShow(tvShowId: String): Observable<DetailTVShowResponse> =
        tvShowApi.detailTVShow(tvShowId)
}