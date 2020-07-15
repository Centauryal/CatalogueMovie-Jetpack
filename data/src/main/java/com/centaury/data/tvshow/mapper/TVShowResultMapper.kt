package com.centaury.data.tvshow.mapper

import com.centaury.data.tvshow.repository.source.network.result.tvshow.ResultsItem
import com.centaury.data.tvshow.repository.source.network.result.tvshow.TVShowResponse
import com.centaury.domain.tvshow.model.TVShow
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
}