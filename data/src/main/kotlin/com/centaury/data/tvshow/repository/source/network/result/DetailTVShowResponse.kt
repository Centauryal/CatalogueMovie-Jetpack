package com.centaury.data.tvshow.repository.source.network.result

import androidx.annotation.Keep
import com.squareup.moshi.Json

@Keep
data class DetailTVShowResponse(

    @field:Json(name = "original_language")
    val originalLanguage: String,

    @field:Json(name = "backdrop_path")
    val backdropPath: String,

    @field:Json(name = "genres")
    val genres: List<TVShowGenresItem>,

    @field:Json(name = "id")
    val id: Int,

    @field:Json(name = "first_air_date")
    val firstAirDate: String,

    @field:Json(name = "overview")
    val overview: String,

    @field:Json(name = "poster_path")
    val posterPath: String,

    @field:Json(name = "original_name")
    val originalName: String,

    @field:Json(name = "vote_average")
    val voteAverage: Double,

    @field:Json(name = "name")
    val name: String
)