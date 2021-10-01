package com.centaury.domain.tvshow

import com.centaury.domain.model.*
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable

/**
 * @Author Centaury (alfa.arnialfa@gmail.com)
 * Created by Centaury on 7/15/2020.
 */
interface TVShowsRepository {

    fun getDiscoveryTVShows(): Observable<List<TVShow>>

    fun getDetailTVShow(tvShowId: Int): Observable<Detail>

    fun getGenreTVShows(): Observable<List<Genre>>

    fun getSearchTVShowsAll(query: String): Observable<List<Search>>

    fun getAllFavoriteTVShow(): Flowable<List<TVShowsDB>>

    fun getFavoriteTVShowById(id: Int): Flowable<TVShowsDB>

    fun insertFavoriteTVShow(tvShow: TVShowsDB): Completable

    fun deleteFavoriteTVShow(tvShow: TVShowsDB): Completable
}