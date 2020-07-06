package com.centaury.data.movies.repository.source.network.result.detail

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DetailMovieResponse(

    @Json(name = "original_language")
    val originalLanguage: String,

    @Json(name = "imdb_id")
    val imdbId: String,

    @Json(name = "video")
    val video: Boolean,

    @Json(name = "title")
    val title: String,

    @Json(name = "backdrop_path")
    val backdropPath: String,

    @Json(name = "revenue")
    val revenue: Int,

    @Json(name = "genres")
    val genres: List<MovieGenresItem>,

    @Json(name = "popularity")
    val popularity: Double,

    @Json(name = "production_countries")
    val productionCountries: List<ProductionCountriesItem>,

    @Json(name = "id")
    val id: Int,

    @Json(name = "vote_count")
    val voteCount: Int,

    @Json(name = "budget")
    val budget: Int,

    @Json(name = "overview")
    val overview: String,

    @Json(name = "original_title")
    val originalTitle: String,

    @Json(name = "runtime")
    val runtime: Int,

    @Json(name = "poster_path")
    val posterPath: String,

    @Json(name = "spoken_languages")
    val spokenLanguages: List<SpokenLanguagesItem>,

    @Json(name = "production_companies")
    val productionCompanies: List<ProductionCompaniesItem>,

    @Json(name = "release_date")
    val releaseDate: String,

    @Json(name = "vote_average")
    val voteAverage: Double,

    @Json(name = "belongs_to_collection")
    val belongsToCollection: Any,

    @Json(name = "tagline")
    val tagline: String,

    @Json(name = "adult")
    val adult: Boolean,

    @Json(name = "homepage")
    val homepage: String,

    @Json(name = "status")
    val status: String
)