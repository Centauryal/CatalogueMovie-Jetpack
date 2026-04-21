package com.centaury.data.tvshow.repository.source.network

import androidx.paging.PagingSource
import com.centaury.data.tvshow.repository.TVShowsEntityData
import com.centaury.data.tvshow.repository.source.local.entity.TVShowEntity
import com.centaury.data.tvshow.repository.source.network.result.DetailTVShowResponse
import com.centaury.data.tvshow.repository.source.network.result.GenreTVShowResponse
import com.centaury.data.tvshow.repository.source.network.result.SearchTVShowResponse
import com.centaury.data.tvshow.repository.source.network.result.TVShowResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * @Author Centaury (alfa.arnialfa@gmail.com)
 * Created by Centaury on 7/15/2020.
 */
class NetworkTVShowsEntityData @Inject constructor(
    private val tvShowApi: TVShowApi
) : TVShowsEntityData {

    override suspend fun discoveryTVShows(page: Int): TVShowResponse =
        tvShowApi.discoveryTVShows(page)

    override suspend fun detailTVShow(tvShowId: Int): DetailTVShowResponse =
        tvShowApi.detailTVShow(tvShowId)

    override suspend fun genreTVShows(): GenreTVShowResponse = tvShowApi.genreTVShows()

    override suspend fun searchTVShowsAll(query: String, page: Int): SearchTVShowResponse =
        tvShowApi.searchTVShowsAll(query)

    override fun getAllFavoriteTVShow(): PagingSource<Int, TVShowEntity> {
        throw UnsupportedOperationException("Favorite tvShow not supported in network source")
    }

    override fun getFavoriteTVShowById(id: Int): Flow<TVShowEntity?> {
        throw UnsupportedOperationException("Favorite tvShow by id not supported in network source")
    }

    override suspend fun insertFavoriteTVShow(tvShowEntity: TVShowEntity) {
        throw UnsupportedOperationException("insert favorite tvShow not supported in network source")
    }

    override suspend fun deleteFavoriteTVShow(tvShowEntity: TVShowEntity) {
        throw UnsupportedOperationException("Delete favorite tvShow not supported in network source")
    }
}