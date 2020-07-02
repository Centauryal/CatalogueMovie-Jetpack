package com.centaury.cataloguemovie.data.remote.detail.movie

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieGenresItem(

    @Json(name = "name")
    val name: String? = null,

    @Json(name = "id")
    val id: Int? = null
)