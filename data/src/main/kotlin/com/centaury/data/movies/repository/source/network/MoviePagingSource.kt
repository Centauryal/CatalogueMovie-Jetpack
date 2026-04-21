package com.centaury.data.movies.repository.source.network

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.centaury.data.movies.mapper.MoviesResultMapper
import com.centaury.data.movies.repository.MoviesEntityData
import com.centaury.domain.model.Movie

class MoviePagingSource(
    private val networkData: MoviesEntityData,
    private val mapper: MoviesResultMapper
) : PagingSource<Int, Movie>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        return try {
            val page = params.key ?: 1
            val response = networkData.discoveryMovies(page)
            val movies = mapper.transformMovie(response)

            LoadResult.Page(
                data = movies,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (movies.isEmpty()) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}
