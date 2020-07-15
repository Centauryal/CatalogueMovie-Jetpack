package com.centaury.data.genre.repository.source.network.result

import com.squareup.moshi.Json

data class GenreResponse(

    @field:Json(name = "genres")
    val genres: List<GenresItem>
)