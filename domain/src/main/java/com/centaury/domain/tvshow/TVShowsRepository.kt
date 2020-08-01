package com.centaury.domain.tvshow

import com.centaury.domain.tvshow.model.TVShow
import com.centaury.domain.tvshow.model.TVShowsEntity
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable

/**
 * @Author Centaury (alfa.arnialfa@gmail.com)
 * Created by Centaury on 7/15/2020.
 */
interface TVShowsRepository {

    fun getDiscoveryTVShows(): Observable<List<TVShow>>

    fun getAllFavoriteTVShow(): Flowable<List<TVShowsEntity>>

    fun getFavoriteTVShowById(id: Int): Flowable<TVShowsEntity>

    fun insertFavoriteTVShow(tvShow: TVShowsEntity): Completable

    fun deleteFavoriteTVShow(tvShow: TVShowsEntity): Completable
}