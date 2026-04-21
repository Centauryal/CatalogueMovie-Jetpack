package com.centaury.data.movies.repository.source.network

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.centaury.data.movies.mapper.MoviesResultMapper
import com.centaury.data.movies.repository.MoviesEntityData
import com.centaury.domain.model.Search

class SearchPagingSource(
    private val networkData: MoviesEntityData,
    private val mapper: MoviesResultMapper,
    private val query: String
) : PagingSource<Int, Search>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Search> {
        return try {
            val page = params.key ?: 1
            val response = networkData.searchMoviesAll(query, page)
            val searchResults = mapper.transformSearchMovie(response)

            LoadResult.Page(
                data = searchResults,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (searchResults.isEmpty()) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Search>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}
