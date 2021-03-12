package com.centaury.data.detail.repository.source.network.result.tvshow

import androidx.annotation.Keep
import com.squareup.moshi.Json

@Keep
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