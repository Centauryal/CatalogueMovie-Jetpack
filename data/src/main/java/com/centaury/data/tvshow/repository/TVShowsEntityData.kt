package com.centaury.data.tvshow.repository

import com.centaury.data.tvshow.repository.source.local.entity.TVShowEntity
import com.centaury.data.tvshow.repository.source.network.result.DetailTVShowResponse
import com.centaury.data.tvshow.repository.source.network.result.GenreTVShowResponse
import com.centaury.data.tvshow.repository.source.network.result.SearchTVShowResponse
import com.centaury.data.tvshow.repository.source.network.result.TVShowResponse
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable

/**
 * @Author Centaury (alfa.arnialfa@gmail.com)
 * Created by Centaury on 7/15/2020.
 */
interface TVShowsEntityData {

    fun discoveryTVShows(): Observable<TVShowResponse>

    fun detailTVShow(tvShowId: Int): Observable<DetailTVShowResponse>

    fun genreTVShows(): Observable<GenreTVShowResponse>

    fun searchTVShowsAll(query: String): Observable<SearchTVShowResponse>

    fun getAllFavoriteTVShow(): Flowable<List<TVShowEntity>>

    fun getFavoriteTVShowById(id: Int): Flowable<TVShowEntity>

    fun insertFavoriteTVShow(tvShowEntity: TVShowEntity): Completable

    fun deleteFavoriteTVShow(tvShowEntity: TVShowEntity): Completable
}