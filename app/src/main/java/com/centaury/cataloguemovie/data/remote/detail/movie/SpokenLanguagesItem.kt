package com.centaury.cataloguemovie.data.remote.detail.movie

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SpokenLanguagesItem(

    @Json(name = "name")
    val name: String? = null,

    @Json(name = "iso_639_1")
    val iso6391: String? = null
)