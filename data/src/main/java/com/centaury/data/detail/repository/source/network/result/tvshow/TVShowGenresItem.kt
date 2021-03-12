package com.centaury.data.detail.repository.source.network.result.tvshow

import androidx.annotation.Keep
import com.squareup.moshi.Json

@Keep
data class TVShowGenresItem(

    @field:Json(name = "name")
    val name: String,

    @field:Json(name = "id")
    val id: Int
)