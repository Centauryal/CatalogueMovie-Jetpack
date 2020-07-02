package com.centaury.cataloguemovie.data.remote.detail.movie

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ProductionCountriesItem(

    @Json(name = "iso_3166_1")
    val iso31661: String? = null,

    @Json(name = "name")
    val name: String? = null
)