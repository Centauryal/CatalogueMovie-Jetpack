package com.centaury.data.movies.repository.source.network.result.genre

import com.squareup.moshi.Json

data class GenresItem(

    @field:Json(name = "name")
    val name: String,

    @field:Json(name = "id")
    val id: Int
)