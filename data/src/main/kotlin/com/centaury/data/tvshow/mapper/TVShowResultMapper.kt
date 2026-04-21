package com.centaury.data.tvshow.mapper

import com.centaury.data.tvshow.repository.source.network.result.DetailTVShowResponse
import com.centaury.data.tvshow.repository.source.network.result.GenreTVShowResponse
import com.centaury.data.tvshow.repository.source.network.result.ResultsItem
import com.centaury.data.tvshow.repository.source.network.result.SearchTVShowResponse
import com.centaury.data.tvshow.repository.source.network.result.SearchTVShowsItem
import com.centaury.data.tvshow.repository.source.network.result.TVShowResponse
import com.centaury.domain.model.Detail
import com.centaury.domain.model.Genre
import com.centaury.domain.model.Search
import com.centaury.domain.model.TVShow
import javax.inject.Inject
import javax.inject.Singleton

/**
 * @Author Centaury (alfa.arnialfa@gmail.com)
 * Created by Centaury on 7/3/2020.
 */
@Singleton
class TVShowResultMapper @Inject constructor() {

    fun transformTVShow(tvShowResponse: TVShowResponse): List<TVShow> =
        tvShowResponse.results.map(::toTVShow)

    private fun toTVShow(resultsItem: ResultsItem) = with(resultsItem) {
        TVShow(
            id = id,
            title = name,
            titleBackground = originalName,
            image = posterPath,
            genre = genreIds,
            overview = overview,
            date = firstAirDate
        )
    }

    fun transformDetailTVShow(detailTVShowResponse: DetailTVShowResponse): Detail =
        with(detailTVShowResponse) {
            Detail(
                id,
                name,
                originalName,
                posterPath,
                backdropPath,
                genres.map { it.name },
                voteAverage,
                voteAverage,
                firstAirDate,
                overview
            )
        }

    fun transformGenreTVShow(genreTVShowResponse: GenreTVShowResponse): List<Genre> =
        genreTVShowResponse.genres.map { Genre(it.id, it.name) }

    fun transformSearchTVShow(searchTVShowResponse: SearchTVShowResponse): List<Search> =
        searchTVShowResponse.results.map(::toSearchTVShow)

    private fun toSearchTVShow(searchTVShowsItem: SearchTVShowsItem) = with(searchTVShowsItem) {
        Search(
            id = id,
            title = name,
            titleBackground = originalName,
            image = posterPath,
            genre = genreIds,
            overview = overview,
            date = firstAirDate
        )
    }
}
