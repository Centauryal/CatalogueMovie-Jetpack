package com.centaury.data.detail.repository.source.network.result.movie

import com.squareup.moshi.Json

data class MovieGenresItem(

    @field:Json(name = "name")
    val name: String,

    @field:Json(name = "id")
    val id: Int
)