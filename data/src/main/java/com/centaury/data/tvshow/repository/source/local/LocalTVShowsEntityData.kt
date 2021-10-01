package com.centaury.data.tvshow.repository.source.local

import com.centaury.data.db.CatalogueDatabase
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
class LocalTVShowsEntityData @Inject constructor(
    private val catalogueDatabase: CatalogueDatabase
) : TVShowsEntityData {
    override fun discoveryTVShows(): Observable<TVShowResponse> {
        TODO("Not yet implemented")
    }

    override fun detailTVShow(tvShowId: Int): Observable<DetailTVShowResponse> {
        TODO("Not yet implemented")
    }

    override fun genreTVShows(): Observable<GenreTVShowResponse> {
        TODO("Not yet implemented")
    }

    override fun searchTVShowsAll(query: String): Observable<SearchTVShowResponse> {
        TODO("Not yet implemented")
    }

    override fun getAllFavoriteTVShow(): Flowable<List<TVShowEntity>> =
        catalogueDatabase.tvShowDao().loadAllTVShow()

    override fun getFavoriteTVShowById(id: Int): Flowable<TVShowEntity> =
        catalogueDatabase.tvShowDao().loadMovieById(id)

    override fun insertFavoriteTVShow(tvShowEntity: TVShowEntity): Completable =
        catalogueDatabase.tvShowDao().insertTVShow(tvShowEntity)

    override fun deleteFavoriteTVShow(tvShowEntity: TVShowEntity): Completable =
        catalogueDatabase.tvShowDao().deleteTVShow(tvShowEntity)

}