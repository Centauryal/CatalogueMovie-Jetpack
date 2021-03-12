package com.centaury.data.detail.repository.source.network.result.movie

import androidx.annotation.Keep
import com.squareup.moshi.Json

@Keep
data class SpokenLanguagesItem(

    @field:Json(name = "name")
    val name: String,

    @field:Json(name = "iso_639_1")
    val iso6391: String
)