package com.centaury.data.tvshow.repository.source.network.result.detail

import com.squareup.moshi.Json

data class ProductionCompaniesItem(

    @field:Json(name = "logo_path")
    val logoPath: Any,

    @field:Json(name = "name")
    val name: String,

    @field:Json(name = "id")
    val id: Int,

    @field:Json(name = "origin_country")
    val originCountry: String
)