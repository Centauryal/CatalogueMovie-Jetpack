package com.centaury.data.tvshow.repository.source.network.result

import com.squareup.moshi.Json

data class ResultsItem(

    @field:Json(name = "first_air_date")
    val firstAirDate: String,

    @field:Json(name = "overview")
    val overview: String,

    @field:Json(name = "original_language")
    val originalLanguage: String,

    @field:Json(name = "genre_ids")
    val genreIds: List<Int>,

    @field:Json(name = "poster_path")
    val posterPath: String,

    @field:Json(name = "origin_country")
    val originCountry: List<String>,

    @field:Json(name = "backdrop_path")
    val backdropPath: String,

    @field:Json(name = "original_name")
    val originalName: String,

    @field:Json(name = "popularity")
    val popularity: Double,

    @field:Json(name = "vote_average")
    val voteAverage: Double,

    @field:Json(name = "name")
    val name: String,

    @field:Json(name = "id")
    val id: Int,

    @field:Json(name = "vote_count")
    val voteCount: Int
)