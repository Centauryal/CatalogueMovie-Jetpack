package com.centaury.data.movies.repository.source.network.result

import androidx.annotation.Keep
import com.squareup.moshi.Json

@Keep
data class MovieGenresItem(

    @field:Json(name = "name")
    val name: String,

    @field:Json(name = "id")
    val id: Int
)