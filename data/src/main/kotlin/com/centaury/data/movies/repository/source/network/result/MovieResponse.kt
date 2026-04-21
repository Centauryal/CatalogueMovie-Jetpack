package com.centaury.data.movies.repository.source.network.result

import androidx.annotation.Keep
import com.squareup.moshi.Json

@Keep
data class MovieResponse(

    @field:Json(name = "page")
    val page: Int,

    @field:Json(name = "total_pages")
    val totalPages: Int,

    @field:Json(name = "results")
    val results: List<ResultsItem>,

    @field:Json(name = "total_results")
    val totalResults: Int
)