package com.centaury.cataloguemovie.data.remote.detail.tvshow

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CreatedByItem(

    @Json(name = "gender")
    val gender: Int? = null,

    @Json(name = "credit_id")
    val creditId: String? = null,

    @Json(name = "name")
    val name: String? = null,

    @Json(name = "profile_path")
    val profilePath: String? = null,

    @Json(name = "id")
    val id: Int? = null
)