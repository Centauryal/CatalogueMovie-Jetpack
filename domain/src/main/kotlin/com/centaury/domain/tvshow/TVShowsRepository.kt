package com.centaury.domain.tvshow

import androidx.paging.PagingData
import com.centaury.domain.model.*
import kotlinx.coroutines.flow.Flow

/**
 * @Author Centaury (alfa.arnialfa@gmail.com)
 * Created by Centaury on 7/15/2020.
 */
interface TVShowsRepository {

    fun getDiscoveryTVShows(): Flow<PagingData<TVShow>>

    suspend fun getDetailTVShow(tvShowId: Int): Detail

    suspend fun getGenreTVShows(): List<Genre>

    fun getSearchTVShowsAll(query: String): Flow<PagingData<Search>>

    fun getAllFavoriteTVShow(): Flow<PagingData<TVShowsDB>>

    fun getFavoriteTVShowById(id: Int): Flow<TVShowsDB?>

    suspend fun insertFavoriteTVShow(tvShow: TVShowsDB)

    suspend fun deleteFavoriteTVShow(tvShow: TVShowsDB)
}