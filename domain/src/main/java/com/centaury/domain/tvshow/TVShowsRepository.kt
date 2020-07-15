package com.centaury.domain.tvshow

import com.centaury.domain.movies.model.Detail
import com.centaury.domain.tvshow.model.TVShow
import io.reactivex.Observable

/**
 * @Author Centaury (alfa.arnialfa@gmail.com)
 * Created by Centaury on 7/15/2020.
 */
interface TVShowsRepository {

    fun getDiscoveryTVShows(): Observable<List<TVShow>>

    fun getDetailTVShow(tvShowId: String): Observable<Detail>
}