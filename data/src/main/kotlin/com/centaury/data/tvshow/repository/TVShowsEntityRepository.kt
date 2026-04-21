package com.centaury.data.tvshow.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.centaury.data.common.Source
import com.centaury.data.tvshow.mapper.TVShowEntityMapper
import com.centaury.data.tvshow.mapper.TVShowResultMapper
import com.centaury.data.tvshow.repository.source.TVShowsDataFactory
import com.centaury.data.tvshow.repository.source.network.TVShowPagingSource
import com.centaury.data.tvshow.repository.source.network.TVShowSearchPagingSource
import com.centaury.domain.model.Detail
import com.centaury.domain.model.Genre
import com.centaury.domain.model.Search
import com.centaury.domain.model.TVShow
import com.centaury.domain.model.TVShowsDB
import com.centaury.domain.tvshow.TVShowsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

/**
 * @Author Centaury (alfa.arnialfa@gmail.com)
 * Created by Centaury on 7/15/2020.
 */
@Singleton
class TVShowsEntityRepository @Inject constructor(
    private val tvShowsDataFactory: TVShowsDataFactory,
    private val tvShowResultMapper: TVShowResultMapper,
    private val tvShowEntityMapper: TVShowEntityMapper
) : TVShowsRepository {

    override fun getDiscoveryTVShows(): Flow<PagingData<TVShow>> = Pager(
        config = PagingConfig(
            pageSize = MAX_PAGE_SIZE,
            enablePlaceholders = false
        ),
        pagingSourceFactory = {
            TVShowPagingSource(createTVShowData(), tvShowResultMapper)
        }
    ).flow

    override suspend fun getDetailTVShow(tvShowId: Int): Detail =
        tvShowResultMapper.transformDetailTVShow(createTVShowData().detailTVShow(tvShowId))

    override suspend fun getGenreTVShows(): List<Genre> =
        tvShowResultMapper.transformGenreTVShow(createTVShowData().genreTVShows())

    override fun getSearchTVShowsAll(query: String): Flow<PagingData<Search>> = Pager(
        config = PagingConfig(
            pageSize = MAX_PAGE_SIZE,
            enablePlaceholders = false
        ),
        pagingSourceFactory = {
            TVShowSearchPagingSource(createTVShowData(), tvShowResultMapper, query)
        }
    ).flow

    override fun getAllFavoriteTVShow(): Flow<PagingData<TVShowsDB>> = Pager(
        config = PagingConfig(
            pageSize = MAX_PAGE_SIZE,
            enablePlaceholders = false
        ),
        pagingSourceFactory = { createEntityTVShowData().getAllFavoriteTVShow() }
    ).flow.map { pagingData ->
        pagingData.map { tvShowEntityMapper.transformEntityTVShowById(it) }
    }

    override fun getFavoriteTVShowById(id: Int): Flow<TVShowsDB?> =
        createEntityTVShowData().getFavoriteTVShowById(id).map { entity ->
            entity?.let { tvShowEntityMapper.transformEntityTVShowById(it) }
        }

    override suspend fun insertFavoriteTVShow(tvShow: TVShowsDB) =
        createEntityTVShowData().insertFavoriteTVShow(
            tvShowEntityMapper.transformTVShowToEntity(
                tvShow
            )
        )

    override suspend fun deleteFavoriteTVShow(tvShow: TVShowsDB) =
        createEntityTVShowData().deleteFavoriteTVShow(
            tvShowEntityMapper.transformTVShowToEntity(tvShow)
        )

    private fun createTVShowData(): TVShowsEntityData =
        tvShowsDataFactory.createData(Source.NETWORK)

    private fun createEntityTVShowData(): TVShowsEntityData =
        tvShowsDataFactory.createData(Source.LOCAL)

    companion object {
        private const val MAX_PAGE_SIZE = 10
    }
}