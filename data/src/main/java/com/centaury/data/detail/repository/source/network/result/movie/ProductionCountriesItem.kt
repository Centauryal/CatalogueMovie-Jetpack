package com.centaury.data.detail.repository.source.network.result.movie

import com.squareup.moshi.Json

data class ProductionCountriesItem(

    @field:Json(name = "iso_3166_1")
    val iso31661: String,

    @field:Json(name = "name")
    val name: String
)