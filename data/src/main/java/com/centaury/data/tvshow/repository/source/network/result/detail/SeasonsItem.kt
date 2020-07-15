package com.centaury.data.tvshow.repository.source.network.result.detail

import com.squareup.moshi.Json

data class SeasonsItem(

    @field:Json(name = "air_date")
    val airDate: Any,

    @field:Json(name = "overview")
    val overview: String,

    @field:Json(name = "episode_count")
    val episodeCount: Int,

    @field:Json(name = "name")
    val name: String,

    @field:Json(name = "season_number")
    val seasonNumber: Int,

    @field:Json(name = "id")
    val id: Int,

    @field:Json(name = "poster_path")
    val posterPath: Any
)