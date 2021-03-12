package com.centaury.data.genre.repository.source.network.result

import androidx.annotation.Keep
import com.squareup.moshi.Json

@Keep
data class GenreResponse(

    @field:Json(name = "genres")
    val genres: List<GenresItem>
)