package com.centaury.data.detail.repository.source.network.result.movie

import com.squareup.moshi.Json

data class SpokenLanguagesItem(

    @field:Json(name = "name")
    val name: String,

    @field:Json(name = "iso_639_1")
    val iso6391: String
)