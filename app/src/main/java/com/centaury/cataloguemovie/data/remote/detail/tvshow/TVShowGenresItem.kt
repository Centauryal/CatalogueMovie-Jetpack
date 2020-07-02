package com.centaury.cataloguemovie.data.remote.detail.tvshow

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TVShowGenresItem(

    @Json(name = "name")
    val name: String? = null,

    @Json(name = "id")
    val id: Int? = null
)