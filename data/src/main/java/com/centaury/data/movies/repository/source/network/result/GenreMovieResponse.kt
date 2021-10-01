package com.centaury.data.movies.repository.source.network.result

import androidx.annotation.Keep
import com.squareup.moshi.Json

@Keep
data class GenreMovieResponse(

    @field:Json(name = "genres")
    val genres: List<MovieGenresItem>
)