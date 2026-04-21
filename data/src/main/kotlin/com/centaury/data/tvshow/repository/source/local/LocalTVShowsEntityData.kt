package com.centaury.data.tvshow.repository.source.local

import androidx.paging.PagingSource
import com.centaury.data.db.CatalogueDatabase
import com.centaury.data.tvshow.repository.TVShowsEntityData
import com.centaury.data.tvshow.repository.source.local.entity.TVShowEntity
import com.centaury.data.tvshow.repository.source.network.result.DetailTVShowResponse
import com.centaury.data.tvshow.repository.source.network.result.GenreTVShowResponse
import com.centaury.data.tvshow.repository.source.network.result.SearchTVShowResponse
import com.centaury.data.tvshow.repository.source.network.result.TVShowResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

/**
 * @Author Centaury (alfa.arnialfa@gmail.com)
 * Created by Centaury on 7/15/2020.
 */
@Singleton
class LocalTVShowsEntityData @Inject constructor(
    private val catalogueDatabase: CatalogueDatabase
) : TVShowsEntityData {
    override suspend fun discoveryTVShows(page: Int): TVShowResponse {
        throw UnsupportedOperationException("Discovery tvShow not supported in local source")
    }

    override suspend fun detailTVShow(tvShowId: Int): DetailTVShowResponse {
        throw UnsupportedOperationException("Detail tvShow not supported in local source")
    }

    override suspend fun genreTVShows(): GenreTVShowResponse {
        throw UnsupportedOperationException("Genre tvShow not supported in local source")
    }

    override suspend fun searchTVShowsAll(query: String, page: Int): SearchTVShowResponse {
        throw UnsupportedOperationException("Search tvShow not supported in local source")
    }

    override fun getAllFavoriteTVShow(): PagingSource<Int, TVShowEntity> =
        catalogueDatabase.tvShowDao().loadAllTVShow()

    override fun getFavoriteTVShowById(id: Int): Flow<TVShowEntity?> =
        catalogueDatabase.tvShowDao().loadMovieById(id)

    override suspend fun insertFavoriteTVShow(tvShowEntity: TVShowEntity) =
        catalogueDatabase.tvShowDao().insertTVShow(tvShowEntity)

    override suspend fun deleteFavoriteTVShow(tvShowEntity: TVShowEntity) =
        catalogueDatabase.tvShowDao().deleteTVShow(tvShowEntity)

}