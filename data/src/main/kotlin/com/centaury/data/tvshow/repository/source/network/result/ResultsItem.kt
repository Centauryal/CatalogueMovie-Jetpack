package com.centaury.data.tvshow.repository.source.network.result

import androidx.annotation.Keep
import com.squareup.moshi.Json

@Keep
data class ResultsItem(

    @field:Json(name = "first_air_date")
    val firstAirDate: String,

    @field:Json(name = "overview")
    val overview: String,

    @field:Json(name = "genre_ids")
    val genreIds: List<Int>,

    @field:Json(name = "poster_path")
    val posterPath: String,

    @field:Json(name = "original_name")
    val originalName: String,

    @field:Json(name = "name")
    val name: String,

    @field:Json(name = "id")
    val id: Int
)