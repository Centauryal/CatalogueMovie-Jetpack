package com.centaury.data.movies.repository.source.network.result.detail

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ProductionCompaniesItem(

    @Json(name = "logo_path")
    val logoPath: Any,

    @Json(name = "name")
    val name: String,

    @Json(name = "id")
    val id: Int,

    @Json(name = "origin_country")
    val originCountry: String
)