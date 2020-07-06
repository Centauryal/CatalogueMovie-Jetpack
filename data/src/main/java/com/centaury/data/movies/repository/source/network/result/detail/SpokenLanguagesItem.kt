package com.centaury.data.movies.repository.source.network.result.detail

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SpokenLanguagesItem(

    @Json(name = "name")
    val name: String,

    @Json(name = "iso_639_1")
    val iso6391: String
)