package com.centaury.data.movies.repository.source.network.result

import androidx.annotation.Keep
import com.squareup.moshi.Json

@Keep
data class ResultsItem(

    @field:Json(name = "overview")
    val overview: String,

    @field:Json(name = "original_title")
    val originalTitle: String,

    @field:Json(name = "title")
    val title: String,

    @field:Json(name = "genre_ids")
    val genreIds: List<Int>,

    @field:Json(name = "poster_path")
    val posterPath: String,

    @field:Json(name = "release_date")
    val releaseDate: String,

    @field:Json(name = "id")
    val id: Int
)