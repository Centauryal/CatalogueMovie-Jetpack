package com.centaury.data.movies.repository.source.network.result

import androidx.annotation.Keep
import com.squareup.moshi.Json

@Keep
data class DetailMovieResponse(

    @field:Json(name = "original_language")
    val originalLanguage: String,

    @field:Json(name = "title")
    val title: String,

    @field:Json(name = "backdrop_path")
    val backdropPath: String,

    @field:Json(name = "genres")
    val genres: List<MovieGenresItem>,

    @field:Json(name = "id")
    val id: Int,

    @field:Json(name = "overview")
    val overview: String,

    @field:Json(name = "original_title")
    val originalTitle: String,

    @field:Json(name = "poster_path")
    val posterPath: String,

    @field:Json(name = "release_date")
    val releaseDate: String,

    @field:Json(name = "vote_average")
    val voteAverage: Double
)