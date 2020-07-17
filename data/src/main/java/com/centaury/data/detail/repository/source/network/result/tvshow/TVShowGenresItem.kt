package com.centaury.data.detail.repository.source.network.result.tvshow

import com.squareup.moshi.Json

data class TVShowGenresItem(

    @field:Json(name = "name")
    val name: String,

    @field:Json(name = "id")
    val id: Int
)