package com.centaury.data.detail.repository.source.network.result.tvshow

import androidx.annotation.Keep
import com.squareup.moshi.Json

@Keep
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