package com.centaury.cataloguemovie.data.remote.detail.movie

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ProductionCompaniesItem(

    @Json(name = "logo_path")
    val logoPath: Any? = null,

    @Json(name = "name")
    val name: String? = null,

    @Json(name = "id")
    val id: Int? = null,

    @Json(name = "origin_country")
    val originCountry: String? = null
)