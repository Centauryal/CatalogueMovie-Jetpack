package com.centaury.cataloguemovie.data.remote.detail.tvshow

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LastEpisodeToAir(

    @Json(name = "production_code")
    val productionCode: String? = null,

    @Json(name = "air_date")
    val airDate: String? = null,

    @Json(name = "overview")
    val overview: String? = null,

    @Json(name = "episode_number")
    val episodeNumber: Int? = null,

    @Json(name = "show_id")
    val showId: Int? = null,

    @Json(name = "vote_average")
    val voteAverage: Double? = null,

    @Json(name = "name")
    val name: String? = null,

    @Json(name = "season_number")
    val seasonNumber: Int? = null,

    @Json(name = "id")
    val id: Int? = null,

    @Json(name = "still_path")
    val stillPath: String? = null,

    @Json(name = "vote_count")
    val voteCount: Int? = null
)