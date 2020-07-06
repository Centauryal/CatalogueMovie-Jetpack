package com.centaury.data.movies.repository.source.network.result.detail

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieGenresItem(

    @Json(name = "name")
    val name: String,

    @Json(name = "id")
    val id: Int
)