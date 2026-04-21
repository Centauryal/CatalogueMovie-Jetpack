package com.centaury.data.tvshow.repository.source.network.result

import androidx.annotation.Keep
import com.squareup.moshi.Json

@Keep
data class TVShowGenresItem(

    @field:Json(name = "name")
    val name: String,

    @field:Json(name = "id")
    val id: Int
)