package com.centaury.data.tvshow.repository.source.network

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.centaury.data.tvshow.mapper.TVShowResultMapper
import com.centaury.data.tvshow.repository.TVShowsEntityData
import com.centaury.domain.model.TVShow

class TVShowPagingSource(
    private val networkData: TVShowsEntityData,
    private val mapper: TVShowResultMapper
) : PagingSource<Int, TVShow>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, TVShow> {
        return try {
            val page = params.key ?: 1
            val response = networkData.discoveryTVShows(page)
            val movies = mapper.transformTVShow(response)

            LoadResult.Page(
                data = movies,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (movies.isEmpty()) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, TVShow>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

}