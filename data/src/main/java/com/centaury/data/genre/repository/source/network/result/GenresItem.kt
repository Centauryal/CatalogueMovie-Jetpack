package com.centaury.data.genre.repository.source.network.result

import com.squareup.moshi.Json

data class GenresItem(

    @field:Json(name = "name")
    val name: String,

    @field:Json(name = "id")
    val id: Int
)