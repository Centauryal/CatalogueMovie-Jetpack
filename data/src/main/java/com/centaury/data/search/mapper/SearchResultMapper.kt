package com.centaury.data.search.mapper

import com.centaury.data.search.repository.source.network.result.SearchMovieResponse
import com.centaury.data.search.repository.source.network.result.SearchMoviesItem
import com.centaury.data.search.repository.source.network.result.SearchTVShowResponse
import com.centaury.data.search.repository.source.network.result.SearchTVShowsItem
import com.centaury.domain.search.model.Search
import javax.inject.Inject

/**
 * @Author Centaury (alfa.arnialfa@gmail.com)
 * Created by Centaury on 2/17/21.
 */
class SearchResultMapper @Inject constructor() {

    fun transformSearchMovie(searchMovieResponse: SearchMovieResponse): List<Search> =
        searchMovieResponse.results.map { it.toSearchMovie() }

    private fun SearchMoviesItem.toSearchMovie() = Search(
        id = this.id,
        title = this.title,
        titleBackground = this.originalTitle,
        image = this.posterPath,
        genre = this.genreIds,
        overview = this.overview,
        date = this.releaseDate
    )

    fun transformSearchTVShow(searchTVShowResponse: SearchTVShowResponse): List<Search> =
        searchTVShowResponse.results.map { it.toSearchTVShow() }

    private fun SearchTVShowsItem.toSearchTVShow() = Search(
        id = this.id,
        title = this.name,
        titleBackground = this.originalName,
        image = this.posterPath,
        genre = this.genreIds,
        overview = this.overview,
        date = this.firstAirDate
    )
}