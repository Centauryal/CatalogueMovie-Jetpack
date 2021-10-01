package com.centaury.data.tvshow.mapper

import com.centaury.data.tvshow.repository.source.network.result.*
import com.centaury.domain.model.Detail
import com.centaury.domain.model.Genre
import com.centaury.domain.model.Search
import com.centaury.domain.model.TVShow
import javax.inject.Inject

/**
 * @Author Centaury (alfa.arnialfa@gmail.com)
 * Created by Centaury on 7/3/2020.
 */
class TVShowResultMapper @Inject constructor() {

    fun transformTVShow(tvShowResponse: TVShowResponse): List<TVShow> =
        tvShowResponse.results.map { it.toTVShow() }

    private fun ResultsItem.toTVShow() = TVShow(
        id = this.id,
        title = this.name,
        titleBackground = this.originalName,
        image = this.posterPath,
        genre = this.genreIds,
        overview = this.overview,
        date = this.firstAirDate
    )

    fun transformDetailTVShow(detailTVShowResponse: DetailTVShowResponse): Detail =
        Detail(
            detailTVShowResponse.id,
            detailTVShowResponse.name,
            detailTVShowResponse.originalName,
            detailTVShowResponse.posterPath,
            detailTVShowResponse.backdropPath,
            listGenreTVShow(detailTVShowResponse.genres),
            detailTVShowResponse.voteAverage,
            detailTVShowResponse.voteAverage,
            detailTVShowResponse.firstAirDate,
            detailTVShowResponse.overview
        )

    private fun listGenreTVShow(itemList: List<TVShowGenresItem>): List<String> {
        val genre: MutableList<String> = ArrayList()

        for (genreItem in itemList) {
            genre.add(genreItem.name)
        }
        return genre
    }

    fun transformGenreTVShow(genreTVShowResponse: GenreTVShowResponse): List<Genre> =
        genreTVShowResponse.genres.map { it.toGenre() }

    private fun TVShowGenresItem.toGenre() =
        Genre(
            id = this.id,
            name = this.name
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