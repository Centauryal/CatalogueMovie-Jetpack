package com.centaury.data.tvshow.repository.source.network.result.detail

import com.squareup.moshi.Json

data class LastEpisodeToAir(

    @field:Json(name = "production_code")
    val productionCode: String,

    @field:Json(name = "air_date")
    val airDate: String,

    @field:Json(name = "overview")
    val overview: String,

    @field:Json(name = "episode_number")
    val episodeNumber: Int,

    @field:Json(name = "show_id")
    val showId: Int,

    @field:Json(name = "vote_average")
    val voteAverage: Double,

    @field:Json(name = "name")
    val name: String,

    @field:Json(name = "season_number")
    val seasonNumber: Int,

    @field:Json(name = "id")
    val id: Int,

    @field:Json(name = "still_path")
    val stillPath: String,

    @field:Json(name = "vote_count")
    val voteCount: Int
)