package com.centaury.cataloguemovie.data.remote.genre

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GenreResponse(

    @Json(name = "genres")
    val genres: List<GenresItem>
)