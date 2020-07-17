package com.centaury.data.detail.repository.source.network

import com.centaury.data.detail.repository.DetailEntityData
import com.centaury.data.detail.repository.source.network.result.movie.DetailMovieResponse
import com.centaury.data.detail.repository.source.network.result.tvshow.DetailTVShowResponse
import io.reactivex.Observable
import javax.inject.Inject

/**
 * @Author Centaury (alfa.arnialfa@gmail.com)
 * Created by Centaury on 7/16/2020.
 */
class NetworkDetailEntityData @Inject constructor(
    private val detailApi: DetailApi
) : DetailEntityData {

    override fun detailMovie(movieId: Int): Observable<DetailMovieResponse> =
        detailApi.detailMovie(movieId)

    override fun detailTVShow(tvShowId: Int): Observable<DetailTVShowResponse> =
        detailApi.detailTVShow(tvShowId)
}