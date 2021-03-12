package com.centaury.data.genre.repository.source.network.result

import androidx.annotation.Keep
import com.squareup.moshi.Json

@Keep
data class GenresItem(

    @field:Json(name = "name")
    val name: String,

    @field:Json(name = "id")
    val id: Int
)