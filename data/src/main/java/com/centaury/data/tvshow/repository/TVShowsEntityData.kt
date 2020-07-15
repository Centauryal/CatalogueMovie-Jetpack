package com.centaury.data.tvshow.repository

import com.centaury.data.tvshow.repository.source.network.result.detail.DetailTVShowResponse
import com.centaury.data.tvshow.repository.source.network.result.tvshow.TVShowResponse
import io.reactivex.Observable

/**
 * @Author Centaury (alfa.arnialfa@gmail.com)
 * Created by Centaury on 7/15/2020.
 */
interface TVShowsEntityData {

    fun discoveryTVShows(): Observable<TVShowResponse>

    fun detailTVShow(tvShowId: String): Observable<DetailTVShowResponse>
}