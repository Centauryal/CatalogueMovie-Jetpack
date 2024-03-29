package com.centaury.data.tvshow.repository.source.network

import com.centaury.data.tvshow.repository.TVShowsEntityData
import com.centaury.data.tvshow.repository.source.local.entity.TVShowEntity
import com.centaury.data.tvshow.repository.source.network.result.DetailTVShowResponse
import com.centaury.data.tvshow.repository.source.network.result.GenreTVShowResponse
import com.centaury.data.tvshow.repository.source.network.result.SearchTVShowResponse
import com.centaury.data.tvshow.repository.source.network.result.TVShowResponse
import io.reactivex.Completable
import io.reactivex.Flowable
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

    override fun detailTVShow(tvShowId: Int): Observable<DetailTVShowResponse> =
        tvShowApi.detailTVShow(tvShowId)

    override fun genreTVShows(): Observable<GenreTVShowResponse> = tvShowApi.genreTVShows()

    override fun searchTVShowsAll(query: String): Observable<SearchTVShowResponse> =
        tvShowApi.searchTVShowsAll(query)

    override fun getAllFavoriteTVShow(): Flowable<List<TVShowEntity>> {
        TODO("Not yet implemented")
    }

    override fun getFavoriteTVShowById(id: Int): Flowable<TVShowEntity> {
        TODO("Not yet implemented")
    }

    override fun insertFavoriteTVShow(tvShowEntity: TVShowEntity): Completable {
        TODO("Not yet implemented")
    }

    override fun deleteFavoriteTVShow(tvShowEntity: TVShowEntity): Completable {
        TODO("Not yet implemented")
    }
}