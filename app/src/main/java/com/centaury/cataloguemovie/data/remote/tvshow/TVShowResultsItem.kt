package com.centaury.cataloguemovie.data.remote.tvshow

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TVShowResultsItem(

    @Json(name = "first_air_date")
    val firstAirDate: String? = null,

    @Json(name = "overview")
    val overview: String? = null,

    @Json(name = "original_language")
    val originalLanguage: String? = null,

    @Json(name = "genre_ids")
    val genreIds: List<Int?>? = null,

    @Json(name = "poster_path")
    val posterPath: String? = null,

    @Json(name = "origin_country")
    val originCountry: List<String?>? = null,

    @Json(name = "backdrop_path")
    val backdropPath: String? = null,

    @Json(name = "original_name")
    val originalName: String? = null,

    @Json(name = "popularity")
    val popularity: Double? = null,

    @Json(name = "vote_average")
    val voteAverage: Double? = null,

    @Json(name = "name")
    val name: String? = null,

    @Json(name = "id")
    val id: Int? = null,

    @Json(name = "vote_count")
    val voteCount: Int? = null
)