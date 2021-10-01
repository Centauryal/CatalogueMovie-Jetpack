package com.centaury.data.tvshow.repository.source.network.result

import androidx.annotation.Keep
import com.squareup.moshi.Json

@Keep
data class GenreTVShowResponse(

    @field:Json(name = "genres")
    val genres: List<TVShowGenresItem>
)