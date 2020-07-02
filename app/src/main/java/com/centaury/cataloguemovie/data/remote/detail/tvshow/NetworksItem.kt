package com.centaury.cataloguemovie.data.remote.detail.tvshow

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NetworksItem(

    @Json(name = "logo_path")
    val logoPath: String? = null,

    @Json(name = "name")
    val name: String? = null,

    @Json(name = "id")
    val id: Int? = null,

    @Json(name = "origin_country")
    val originCountry: String? = null
)