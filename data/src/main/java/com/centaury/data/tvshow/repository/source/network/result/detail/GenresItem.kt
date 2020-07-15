package com.centaury.data.tvshow.repository.source.network.result.detail

import com.squareup.moshi.Json

data class GenresItem(

    @field:Json(name = "name")
    val name: String,

    @field:Json(name = "id")
    val id: Int
)