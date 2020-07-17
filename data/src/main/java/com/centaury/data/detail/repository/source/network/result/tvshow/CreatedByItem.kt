package com.centaury.data.detail.repository.source.network.result.tvshow

import com.squareup.moshi.Json

data class CreatedByItem(

    @field:Json(name = "gender")
    val gender: Int,

    @field:Json(name = "credit_id")
    val creditId: String,

    @field:Json(name = "name")
    val name: String,

    @field:Json(name = "profile_path")
    val profilePath: Any,

    @field:Json(name = "id")
    val id: Int
)