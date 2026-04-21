package com.centaury.data.tvshow.repository

import androidx.paging.PagingSource
import com.centaury.data.tvshow.repository.source.local.entity.TVShowEntity
import com.centaury.data.tvshow.repository.source.network.result.DetailTVShowResponse
import com.centaury.data.tvshow.repository.source.network.result.GenreTVShowResponse
import com.centaury.data.tvshow.repository.source.network.result.SearchTVShowResponse
import com.centaury.data.tvshow.repository.source.network.result.TVShowResponse
import kotlinx.coroutines.flow.Flow

/**
 * @Author Centaury (alfa.arnialfa@gmail.com)
 * Created by Centaury on 7/15/2020.
 */
interface TVShowsEntityData {

    suspend fun discoveryTVShows(page: Int): TVShowResponse

    suspend fun detailTVShow(tvShowId: Int): DetailTVShowResponse

    suspend fun genreTVShows(): GenreTVShowResponse

    suspend fun searchTVShowsAll(query: String, page: Int): SearchTVShowResponse

    fun getAllFavoriteTVShow(): PagingSource<Int, TVShowEntity>

    fun getFavoriteTVShowById(id: Int): Flow<TVShowEntity?>

    suspend fun insertFavoriteTVShow(tvShowEntity: TVShowEntity)

    suspend fun deleteFavoriteTVShow(tvShowEntity: TVShowEntity)
}
