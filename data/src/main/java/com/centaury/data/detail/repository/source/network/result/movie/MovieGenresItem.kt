package com.centaury.data.detail.repository.source.network.result.movie

import androidx.annotation.Keep
import com.squareup.moshi.Json

@Keep
data class MovieGenresItem(

    @field:Json(name = "name")
    val name: String,

    @field:Json(name = "id")
    val id: Int
)