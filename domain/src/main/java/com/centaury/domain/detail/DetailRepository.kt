package com.centaury.domain.detail

import com.centaury.domain.detail.model.Detail
import io.reactivex.Observable

/**
 * @Author Centaury (alfa.arnialfa@gmail.com)
 * Created by Centaury on 7/16/2020.
 */
interface DetailRepository {

    fun getDetailMovie(movieId: Int): Observable<Detail>

    fun getDetailTVShow(tvShowId: Int): Observable<Detail>
}