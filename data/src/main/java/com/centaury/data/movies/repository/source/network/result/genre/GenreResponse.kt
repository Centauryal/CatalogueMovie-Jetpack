package com.centaury.data.movies.repository.source.network.result.genre

import com.squareup.moshi.Json

data class GenreResponse(

    @field:Json(name = "genres")
    val genres: List<GenresItem>
)