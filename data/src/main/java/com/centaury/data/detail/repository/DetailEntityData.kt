package com.centaury.data.detail.repository

import com.centaury.data.detail.repository.source.network.result.movie.DetailMovieResponse
import com.centaury.data.detail.repository.source.network.result.tvshow.DetailTVShowResponse
import io.reactivex.Observable

/**
 * @Author Centaury (alfa.arnialfa@gmail.com)
 * Created by Centaury on 7/16/2020.
 */
interface DetailEntityData {

    fun detailMovie(movieId: Int): Observable<DetailMovieResponse>

    fun detailTVShow(tvShowId: Int): Observable<DetailTVShowResponse>
}